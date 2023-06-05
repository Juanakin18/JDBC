package uo.ri.cws.application.business.invoice.crud.commands;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto;
import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto.InvoiceState;
import uo.ri.cws.application.business.invoice.VehicleAssembler;
import uo.ri.cws.application.business.invoice.assembler.InvoicingAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;
import uo.ri.cws.application.business.vehicletype.VehicleTypeService.VehicleTypeBLDto;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.persistence.InterventionGateway;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.SparePartGateway;
import uo.ri.cws.application.persistence.VehicleGateway;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway.InvoiceDALDto;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway.WorkOrderDALDto;
import uo.ri.cws.application.persistence.workorder.assembler.WorkOrderAssembler;

public class CreateInvoice implements Command<Optional<InvoiceBLDto>> {

	private WorkOrderGateway wog = PersistenceFactory.forWorkOrder();
	private InvoiceGateway igw = PersistenceFactory.forInvoice();
	private InterventionGateway ingtw = PersistenceFactory.forIntervention();
	private VehicleGateway vgtw = PersistenceFactory.forVehicle();
	private VehicleTypeGateway vtgtw = PersistenceFactory.forVehicleType();
	private SubstitutionGateway sgtw = PersistenceFactory.forSubstitution();
	private SparePartGateway spgtw = PersistenceFactory.forSparePart();
	private List<String> workorders;
	private List<WorkOrderBLDto> workOrders = new ArrayList<WorkOrderBLDto>();
	
	public CreateInvoice(List<String> workordersIds) {
		Argument.isNotNull(workordersIds);
		Argument.isTrue(workordersIds.size()>0);
		this.workorders = workordersIds;
	}

	private boolean checkWorkorderExists(String s) throws BusinessException {
		Optional<WorkOrderDALDto> dto = wog.findById(s);
		if(!dto.isEmpty() ) {
			if(dto.get().state.equals("FINISHED")) {
				return true;
			}
			
		}
		throw new BusinessException("HAY UNA WORKORDER INEXISTENTE");
	}

	@Override
	public Optional<InvoiceBLDto> execute() throws BusinessException {
		InvoiceBLDto dto = new InvoiceBLDto();
		dto.id = UUID.randomUUID().toString();
		for(String s : workorders) {
			Argument.isNotEmpty(s);
			if(checkWorkorderExists(s))
				markAsInvoiced(s,dto);
		}
		if(workOrders.size()==0)
			throw new BusinessException("No hay workorders");
		insertInvoice(dto);
		return Optional.of(dto);
	}

	private void markAsInvoiced(String s, InvoiceBLDto dto) {
		
		WorkOrderBLDto wdto = WorkOrderAssembler.toWorkOrderBLDto(wog.findById(s).get()) ;
		wdto.invoiceId = dto.id;
		wdto.version++;
		if(wdto.total==0)
			setAmount(wdto);
		workOrders.add(wdto);
	}

	private void setAmount(WorkOrderBLDto wdto) {
		double manoDeObra = calcularManoDeObra(wdto);
		double sustituciones = calcularSustituciones(wdto);
		wdto.total = manoDeObra + sustituciones;
		
	}

	private double calcularSustituciones(WorkOrderBLDto wdto) {
		double total = 0;
		List<InterventionBLDto> interventions = InterventionAssembler.toBLDtoList(ingtw.findByWorkOrder(wdto.id));
		for(InterventionBLDto dto : interventions) {
			List<SubstitutionBLDto> sustituciones = SubstitutionAssembler.toBLDtoList(sgtw.findByIntervention(dto.id));
			for(SubstitutionBLDto substitution : sustituciones) {
				SparePartBLDto sparePart = SparePartAssembler.toBLDto(spgtw.findById(substitution.sparepart_id)).get();
				total += substitution.quantity* sparePart.price;
			}
		}
		return total;
	}

	private double calcularManoDeObra(WorkOrderBLDto wdto) {
		double total = 0;
		List<InterventionBLDto> interventions = InterventionAssembler.toBLDtoList(ingtw.findByWorkOrder(wdto.id));
		VehicleBLDto vehicles = VehicleAssembler.toBLDto(vgtw.findById(wdto.vehicleId)).get();
		VehicleTypeBLDto vehicleType = VehicleTypeAssembler.toBLDto(vtgtw.findById(vehicles.vehicleTypeId));
		for(InterventionBLDto dto : interventions) {
			total+=dto.minutes*vehicleType.pricePerHour;
		}
		return total;
	}

	private InvoiceBLDto insertInvoice(InvoiceBLDto blddto) {
		blddto.version++;
		
		blddto.date = LocalDate.now();
		setInvoiceAmount(blddto);
		setNumber(blddto);
		blddto.state = InvoiceState.NOT_YET_PAID;
		InvoiceDALDto dto = InvoicingAssembler.toDALDto(blddto);
		dto.date= LocalDate.now();
		igw.add(dto);
		for(WorkOrderBLDto workorder : workOrders) {
			workorder.invoiceId = dto.id;
			workorder.state = "INVOICED";
			wog.update(WorkOrderAssembler.toVehicleDALDto(workorder));
		}
		return InvoicingAssembler.toDto(dto);
	}

	private void setNumber(InvoiceBLDto blddto) {
		List<InvoiceBLDto> dtos = InvoicingAssembler.toInvoicingBLDtoList(igw.findAll());
		if(dtos.size()==0)
			blddto.number=1;
		else
			blddto.number = dtos.get(dtos.size()-1).number+1;
	}

	private void setInvoiceAmount(InvoiceBLDto blddto) {
		double total = 0;
		for(WorkOrderBLDto workOrder  : workOrders) {
			total+=workOrder.total;
		}
		
		if(blddto.date.isBefore(LocalDate.of(2012, 7, 1))) {
			total*=1.18;
		}else
			total*=1.21;
		
		blddto.total = total;
		total = Math.round(total*100.0)/100.0;
	}

}
