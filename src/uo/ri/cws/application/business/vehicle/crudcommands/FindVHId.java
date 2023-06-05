package uo.ri.cws.application.business.vehicle.crudcommands;

import java.util.List;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.crud.commands.VehicleTypeAssembler;
import uo.ri.cws.application.business.invoice.crud.commands.VehicleTypeGateway;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class FindVHId implements Command<List<uo.ri.cws.application.business.vehicletype.VehicleTypeService.VehicleTypeBLDto>> {

	private VehicleTypeGateway g = PersistenceFactory.forVehicleType();
	private String dto;
	public FindVHId(String dto) {
		Argument.isNotNull(dto);
		this.dto = dto;
	}

	@Override
	public List<uo.ri.cws.application.business.vehicletype.VehicleTypeService.VehicleTypeBLDto> execute() throws BusinessException {
		
		return VehicleTypeAssembler.toBLDtolist(g.findById(dto));
	}

}
