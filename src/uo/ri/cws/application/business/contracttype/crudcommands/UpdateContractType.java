package uo.ri.cws.application.business.contracttype.crudcommands;

import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contracttype.ContractTypeAssembler;
import uo.ri.cws.application.persistence.contracttype.ContractTypeDALDto;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway;

public class UpdateContractType implements Command<Void> {

	private ContractTypeBLDto dto;
	private ContractTypeGateway gtw = PersistenceFactory.forContractType();
	public UpdateContractType(ContractTypeBLDto dto) {
		Argument.isNotNull(dto);
		Argument.isNotEmpty(dto.name);
		Argument.isTrue(dto.compensationDays>=0f);
		this.dto =dto;
		
	} 

	@Override
	public Void execute() throws BusinessException {
		checkIfExists();
		dto.version++;
		gtw.update(ContractTypeAssembler.toContractTypeDALDto(dto));
		return null;
	}

	private void checkIfExists() throws BusinessException {
		Optional<ContractTypeDALDto> dto1 = gtw.findByName(dto.name);
		if(dto1.isEmpty())
			throw new BusinessException("No existe el tipo");
		
	}

}
