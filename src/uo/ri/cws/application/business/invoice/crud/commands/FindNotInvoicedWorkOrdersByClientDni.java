package uo.ri.cws.application.business.invoice.crud.commands;

import java.util.ArrayList;
import java.util.List;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.VehicleAssembler;
import uo.ri.cws.application.business.invoice.assembler.WorkOrderForInvoicingBLDto;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.VehicleGateway;
import uo.ri.cws.application.persistence.client.ClientAssembler;
import uo.ri.cws.application.persistence.client.ClientGateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.assembler.WorkOrderAssembler;

public class FindNotInvoicedWorkOrdersByClientDni implements Command<List<WorkOrderForInvoicingBLDto>> {

	private WorkOrderGateway wgw = PersistenceFactory.forWorkOrder();
	private ClientGateway cgtw = PersistenceFactory.forClient();
	private VehicleGateway vgtw = PersistenceFactory.forVehicle();
	private String dni;
	private String clientId;
	public FindNotInvoicedWorkOrdersByClientDni(String arg) {
		Argument.isNotEmpty(arg,"El dni no puede ser nulo");
		this.dni = arg;
		
	}
	private void CheckDniExists() throws BusinessException {
			if(cgtw.findByDni(dni).isEmpty())
				throw new BusinessException("No existe ese dni");
			else
				this.clientId = ClientAssembler.toBLDto(cgtw.findByDni(dni).get()).id;
		
	}
	@Override
	public List<WorkOrderForInvoicingBLDto> execute() throws BusinessException {
		CheckDniExists();
		List<WorkOrderBLDto> list = GetNotInvoicedWorkorderIds(clientId); 
		if(list.size()==0)
			return new ArrayList<WorkOrderForInvoicingBLDto>();
		else {
			list = getNotInvoicedWorkorders(list);
			if(list.size()==0)
				throw new BusinessException("No tiene ninguna");
		}
		return WorkOrderAssembler.toInvoicingBLDto(list);
	}
	private List<WorkOrderBLDto> getNotInvoicedWorkorders(List<WorkOrderBLDto> list) {
		List<WorkOrderBLDto> wdtos = new ArrayList<WorkOrderBLDto>();
			for(WorkOrderBLDto dto : list) {
				if(dto.state.equals("FINISHED")) {
					wdtos.add(dto);
				}
			}
			return wdtos;
		}
	
	
	private List<WorkOrderBLDto> GetNotInvoicedWorkorderIds(String clientId2) {
		List<VehicleBLDto> vdtos = VehicleAssembler.toBLDtoList(vgtw.findByClient(clientId2));
		List<WorkOrderBLDto> wdtos = new ArrayList<WorkOrderBLDto>();
		for(VehicleBLDto vdto: vdtos) {
			List<WorkOrderBLDto> dtos = WorkOrderAssembler.toWorkOrderBLDtoList(wgw.findByVehicleId(vdto.id));
			for(WorkOrderBLDto dto : dtos) {
				if(dto.state.equals("FINISHED")) {
					wdtos.add(dto);
				}
			}
		}
		return wdtos;
	}

}
