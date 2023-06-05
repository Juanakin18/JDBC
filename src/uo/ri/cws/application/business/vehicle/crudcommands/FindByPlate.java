package uo.ri.cws.application.business.vehicle.crudcommands;

import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;

public class FindByPlate implements Command<Optional<VehicleBLDto>> {

	public FindByPlate(String plate) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Optional<VehicleBLDto> execute() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	} 

}
