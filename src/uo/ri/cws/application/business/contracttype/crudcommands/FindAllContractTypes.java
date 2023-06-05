package uo.ri.cws.application.business.contracttype.crudcommands;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contracttype.ContractTypeAssembler;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway;

public class FindAllContractTypes implements Command<List<ContractTypeBLDto>> {

	private ContractTypeGateway gtw = PersistenceFactory.forContractType();
	
	@Override
	public List<ContractTypeBLDto> execute() throws BusinessException {
		return ContractTypeAssembler.toBLDtoList(gtw.findAll());
	}

} 
