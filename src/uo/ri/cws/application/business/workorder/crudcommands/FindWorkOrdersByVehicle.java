package uo.ri.cws.application.business.workorder.crudcommands;

import java.util.List;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.VehicleAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.VehicleGateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.assembler.WorkOrderAssembler;

public class FindWorkOrdersByVehicle implements Command<List<WorkOrderBLDto>> {

	private WorkOrderGateway gtw = PersistenceFactory.forWorkOrder();
	private VehicleGateway vgtw = PersistenceFactory.forVehicle();
	private String plate;
	public FindWorkOrdersByVehicle(String plate) {
		Argument.isNotEmpty(plate);
		this.plate = plate;
	}

	@Override
	public List<WorkOrderBLDto> execute() throws BusinessException {
		VehicleBLDto dto = VehicleAssembler.toBLDto(vgtw.findByPlateNumber(plate)).get() ;
		return (WorkOrderAssembler.toWorkOrderBLDtoList( gtw.findByVehicleId(dto.id))) ;
	}
 

}
