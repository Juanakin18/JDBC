package uo.ri.cws.application.business.workorder.crudcommands;

import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.assembler.WorkOrderAssembler;

public class AddWorkOrder implements Command<Optional<WorkOrderBLDto>> {

	private WorkOrderGateway gtw = PersistenceFactory.forWorkOrder();
	private WorkOrderBLDto plate;
	public AddWorkOrder(WorkOrderBLDto plate) {
		Argument.isNotNull(plate);
		this.plate = plate;
	}

	@Override
	public Optional<WorkOrderBLDto> execute() throws BusinessException {
		 gtw.add(WorkOrderAssembler.toVehicleDALDto(plate));
		return Optional.of(plate);
	}
 
}
