package uo.ri.cws.application.business.workorder.crudcommands;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;

public class DeleteWorkOrder implements Command<Void> {

	private WorkOrderGateway gtw = PersistenceFactory.forWorkOrder();
	private String plate;
	public DeleteWorkOrder(String dto) {
		Argument.isNotEmpty(dto);
		this.plate = dto;
	}

	@Override
	public Void execute() throws BusinessException {
		gtw.remove(plate);
		return null;
	}

}
