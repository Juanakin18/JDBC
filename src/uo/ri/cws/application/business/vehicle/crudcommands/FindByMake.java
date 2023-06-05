package uo.ri.cws.application.business.vehicle.crudcommands;

import java.util.List;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.VehicleAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.VehicleGateway;

public class FindByMake implements Command<List<VehicleBLDto>> {
	private VehicleGateway vgtw = PersistenceFactory.forVehicle();
	private String make;
	public FindByMake(String make) {
		Argument.isNotEmpty(make);
		this.make = make;
	}

	@Override
	public List<VehicleBLDto> execute() throws BusinessException {
		
		return VehicleAssembler.toBLDtoList(vgtw.findByMake(make));
	}
 
}
