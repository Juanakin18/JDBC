package uo.ri.cws.application.business.vehicletype;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.CommandExecutor;
import uo.ri.cws.application.business.vehicle.crudcommands.FindVHId;
import uo.ri.cws.application.business.vehicle.crudcommands.UpdateVH;
import uo.ri.cws.application.business.vehicletype.crudcommands.AddVehicleType;
import uo.ri.cws.application.business.vehicletype.crudcommands.DeleteVehicleType;
public class VehicleTypeServiceImpl implements VehicleTypeService {

	private CommandExecutor exe = new CommandExecutor();
	@Override
	public void addVehicleType(uo.ri.cws.application.business.vehicletype.VehicleTypeService.VehicleTypeBLDto dto) throws BusinessException {
		exe.execute(new AddVehicleType(dto));
		
	}

	@Override
	public void deleteVehicleType(String dni) throws BusinessException {
		exe.execute(new DeleteVehicleType(dni));
		
	}

	@Override
	public List<uo.ri.cws.application.business.vehicletype.VehicleTypeService.VehicleTypeBLDto> findById(String dni) throws BusinessException {
		return exe.execute(new FindVHId(dni));
		
	}

	

	@Override
	public void update(VehicleTypeBLDto dto) throws BusinessException {
		exe.execute(new UpdateVH(dto));
		
	}

}
