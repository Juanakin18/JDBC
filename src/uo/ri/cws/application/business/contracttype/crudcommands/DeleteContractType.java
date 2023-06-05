package uo.ri.cws.application.business.contracttype.crudcommands;

import java.util.List;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.ContractService.ContractState;
import uo.ri.cws.application.business.payroll.ContractAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.ContractGateway;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway;

public class DeleteContractType implements Command<Void> {
	private String name;
	private ContractTypeGateway gtw = PersistenceFactory.forContractType();
	private ContractGateway cgtw = PersistenceFactory.forContract();
	public DeleteContractType(String name) {
		Argument.isNotEmpty(name, "El nombre está vacío");
		this.name = name;
	}

	@Override
	public Void execute() throws BusinessException {
		checkIfExists(name);
		checkIfHasContracts(name);
		checkIfHasContractsTerminated(name);
		gtw.remove(name);
		return null;
	}

	private void checkIfHasContractsTerminated(String name2) throws BusinessException {
		List<ContractBLDto> dtos = ContractAssembler.toBLDtoList(cgtw.findByContractTypeId(name2)) ;
		for(ContractBLDto dto : dtos) {
			if(dto.state != ContractState.TERMINATED)
				throw new BusinessException("Tiene contratos no terminados");
		}
			
		
	}

	private void checkIfHasContracts(String name2) throws BusinessException {
		if(!cgtw.findByContractTypeId(name2).isEmpty())
			throw new BusinessException("No existe ese contrato");
		
	}

	private void checkIfExists(String name2) throws BusinessException {
		if(gtw.findByName(name2).isEmpty())
			throw new BusinessException("No existe ese tipo de contrato");
		
	}

}
