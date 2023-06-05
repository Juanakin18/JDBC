package uo.ri.cws.application.business.sparepart.crudcommands;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.SparePartGateway;

public class DeleteSparePart implements Command<Void> {
	private SparePartGateway gtw = PersistenceFactory.forSparePart();
	private String dto;
	public DeleteSparePart(String dto) {
		Argument.isNotNull(dto);
		this.dto = dto;
	}
	@Override
	public Void execute() throws BusinessException {
		gtw.remove(dto);
		return null;
	}
}
