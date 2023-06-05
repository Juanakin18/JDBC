package uo.ri.cws.application.business.vehicle.crudcommands;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.crud.commands.VehicleTypeAssembler;
import uo.ri.cws.application.business.invoice.crud.commands.VehicleTypeGateway;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.vehicletype.VehicleTypeService.VehicleTypeBLDto;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class UpdateVH implements Command<Void> {

	private VehicleTypeGateway g = PersistenceFactory.forVehicleType();
	private VehicleTypeBLDto dto;
	public UpdateVH(VehicleTypeBLDto dto) {
		Argument.isNotNull(dto);
		this.dto = dto;
	}

	@Override
	public Void execute() throws BusinessException {
		g.update(VehicleTypeAssembler.toDALDto(dto));
		return null;
	}

}
