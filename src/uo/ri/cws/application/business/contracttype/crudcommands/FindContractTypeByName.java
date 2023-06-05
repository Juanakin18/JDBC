package uo.ri.cws.application.business.contracttype.crudcommands;

import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contracttype.ContractTypeAssembler;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway;

public class FindContractTypeByName implements Command<Optional<ContractTypeBLDto>> {

	private String name;
	private ContractTypeGateway gtw = PersistenceFactory.forContractType();
	private Optional<ContractTypeBLDto> result;
	public FindContractTypeByName(String name) {
		Argument.isNotEmpty(name, "El nombre está vacío");
		this.name = name;
		 
	}

	@Override
	public Optional<ContractTypeBLDto> execute() throws BusinessException {
		
			if(gtw.findByName(name).isEmpty()) {
				result = Optional.empty();
			}
				
			else {
				ContractTypeBLDto dto = ContractTypeAssembler.toBLDto(gtw.findByName(name).get());
				result =  Optional.of(dto);
			}
			return result;
		
		
	}

	public Optional<ContractTypeBLDto> get() {
		return result;
	}

}
