package uo.ri.cws.application.business.workorder.crudcommands;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.assembler.WorkOrderAssembler;

public class UpdateWorkOrder implements Command<Void> {

	private WorkOrderGateway gtw = PersistenceFactory.forWorkOrder();
	private WorkOrderBLDto plate;
	public UpdateWorkOrder(WorkOrderBLDto plate) {
		Argument.isNotNull(plate);
		this.plate = plate;
	}

	@Override
	public Void execute() throws BusinessException {
		 gtw.update(WorkOrderAssembler.toVehicleDALDto(plate));
		return null;
	}
 
}
