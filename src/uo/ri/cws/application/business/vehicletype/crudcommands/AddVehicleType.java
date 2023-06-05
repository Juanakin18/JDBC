package uo.ri.cws.application.business.vehicletype.crudcommands;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.crud.commands.VehicleTypeAssembler;
import uo.ri.cws.application.business.invoice.crud.commands.VehicleTypeGateway;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class AddVehicleType implements Command<Void> {

	private VehicleTypeGateway g = PersistenceFactory.forVehicleType();
	private uo.ri.cws.application.business.vehicletype.VehicleTypeService.VehicleTypeBLDto dto;
	public AddVehicleType(uo.ri.cws.application.business.vehicletype.VehicleTypeService.VehicleTypeBLDto dto) {
		Argument.isNotNull(dto);
		this.dto = dto;
	}

	@Override
	public Void execute() throws BusinessException {
		g.add(VehicleTypeAssembler.toDALDto(dto));
		return null;
	}
 
}
