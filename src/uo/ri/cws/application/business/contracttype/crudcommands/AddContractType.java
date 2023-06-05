package uo.ri.cws.application.business.contracttype.crudcommands;

import java.util.UUID;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contracttype.ContractTypeAssembler;
import uo.ri.cws.application.persistence.contracttype.ContractTypeDALDto;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway;

public class AddContractType implements Command<ContractTypeBLDto> {

	private ContractTypeBLDto dto;
	private ContractTypeGateway gtw = PersistenceFactory.forContractType();
	public AddContractType(ContractTypeBLDto dto2) {
		Argument.isNotNull(dto2);
		Argument.isNotEmpty(dto2.name);
		Argument.isTrue(dto2.compensationDays>=0f);
		
		this.dto = dto2;
		 
	}

	@Override
	public ContractTypeBLDto execute() throws BusinessException {
			checkIfExists();
			dto.id = UUID.randomUUID().toString();
			dto.version = 1l;
			ContractTypeDALDto dto1 = ContractTypeAssembler.toContractTypeDALDto(dto);
			gtw.add(dto1);
			return dto;
	}

	private void checkIfExists() throws BusinessException {
			if(!gtw.findByName(dto.name).isEmpty())
				throw new BusinessException("Ya existe un tipo con ese nombre");
		
		
	}

}
