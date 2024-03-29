package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService;
import uo.ri.cws.application.business.util.command.CommandExecutor;
import uo.ri.cws.application.service.util.FindMechanicById;

public class MechanicServiceImpl implements MechanicService {

	private CommandExecutor executor = new CommandExecutor();
	@Override
	public MechanicBLDto addMechanic(MechanicBLDto mechanic) throws BusinessException {
		return executor.execute(new AddMechanic(mechanic));
	}

	@Override
	public void deleteMechanic(String idMechanic) throws BusinessException {
		executor.execute(new DeleteMechanic(idMechanic));
	}

	@Override
	public void updateMechanic(MechanicBLDto mechanic) throws BusinessException {
		executor.execute(new UpdateMechanic(mechanic));
	}

	@Override
	public Optional<MechanicBLDto> findMechanicById(String idMechanic) throws BusinessException {
		return executor.execute(new FindMechanicById(idMechanic));
	}

	@Override
	public Optional<MechanicBLDto> findMechanicByDni(String dniMechanic) throws BusinessException {
		return executor.execute(new FindMechanicByDni(dniMechanic));
		
	}

	@Override
	public List<MechanicBLDto> findAllMechanics() throws BusinessException {
		return executor.execute(new FindAllMechanics());
	}

	@Override
	public List<MechanicBLDto> findMechanicsInProfessionalGroups(String string) throws BusinessException {
		return executor.execute(new FindMechanicInProfessionalGroups(string));
	}

	@Override
	public List<MechanicBLDto> findMechanicsWithContractInForceInContractType(String string) throws BusinessException {
		return executor.execute(new FindMechanicsWithContractInForceInContractType(string));
	}

	@Override
	public List<MechanicBLDto> findMechanicsInForce() throws BusinessException {
		return executor.execute(new FindMechanicsInForce());
	}

	

	

}
