package uo.ri.cws.application.business.contracttype;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contracttype.crudcommands.AddContractType;
import uo.ri.cws.application.business.contracttype.crudcommands.DeleteContractType;
import uo.ri.cws.application.business.contracttype.crudcommands.FindAllContractTypes;
import uo.ri.cws.application.business.contracttype.crudcommands.FindContractTypeByName;
import uo.ri.cws.application.business.contracttype.crudcommands.FindMechanicByContractType;
import uo.ri.cws.application.business.contracttype.crudcommands.UpdateContractType;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.util.command.CommandExecutor;

public class ContractTypeServiceImpl implements ContractTypeService {

	private CommandExecutor executor = new CommandExecutor();
	@Override
	public ContractTypeBLDto addContractType(ContractTypeBLDto dto) throws BusinessException {
		return executor.execute(new AddContractType(dto));
	}

	@Override
	public void deleteContractType(String name) throws BusinessException {
		executor.execute(new DeleteContractType(name));

	}

	@Override
	public void updateContractType(ContractTypeBLDto dto) throws BusinessException {
		executor.execute(new UpdateContractType(dto));

	}

	@Override
	public Optional<ContractTypeBLDto> findContractTypeByName(String name) throws BusinessException {
		// TODO Auto-generated method stub
		return executor.execute(new FindContractTypeByName(name));
	}

	@Override
	public List<ContractTypeBLDto> findAllContractTypes() throws BusinessException {
		
		return executor.execute(new FindAllContractTypes());
	}

	@Override
	public List<MechanicBLDto> findMechanicByContractType(String name) throws BusinessException {
		// TODO Auto-generated method stub
		return executor.execute(new FindMechanicByContractType(name));
	}

}
