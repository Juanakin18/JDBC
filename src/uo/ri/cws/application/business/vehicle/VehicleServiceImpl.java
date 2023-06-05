package uo.ri.cws.application.business.vehicle;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.CommandExecutor;
import uo.ri.cws.application.business.vehicle.crudcommands.FindByClientDNI;
import uo.ri.cws.application.business.vehicle.crudcommands.FindByMake;
import uo.ri.cws.application.business.vehicle.crudcommands.FindByPlate;

public class VehicleServiceImpl implements VehicleService {

	private CommandExecutor executor = new CommandExecutor();
	@Override
	public List<VehicleBLDto> findByClientDni(String dni) throws BusinessException {
		return executor.execute(new FindByClientDNI(dni));
	}

	@Override
	public List<VehicleBLDto> findByMake(String make) throws BusinessException {
		return executor.execute(new FindByMake(make));
	}

	@Override
	public Optional<VehicleBLDto> findVehicleByPlate(String plate) throws BusinessException {
		// TODO Auto-generated method stub
		return executor.execute(new FindByPlate(plate));
	}

}
