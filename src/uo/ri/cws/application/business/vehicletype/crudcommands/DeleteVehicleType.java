package uo.ri.cws.application.business.vehicletype.crudcommands;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.crud.commands.VehicleTypeGateway;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class DeleteVehicleType implements Command<Void> {

	private VehicleTypeGateway g = PersistenceFactory.forVehicleType();
	private String dto;
	public DeleteVehicleType(String dto) {
		Argument.isNotNull(dto);
		this.dto = dto;
	}

	@Override
	public Void execute() throws BusinessException {
		g.remove(dto);
		return null;
	}

}
