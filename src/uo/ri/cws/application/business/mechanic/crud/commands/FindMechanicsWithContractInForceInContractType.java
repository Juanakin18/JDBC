package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.ContractService.ContractState;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.payroll.ContractAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.ContractGateway;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contracttype.ContractTypeDALDto;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class FindMechanicsWithContractInForceInContractType implements Command<List<MechanicBLDto>> {

	private MechanicGateway gtw = PersistenceFactory.forMechanic();
	private ContractGateway cgtw = PersistenceFactory.forContract();
	private ContractTypeGateway ctgtw = PersistenceFactory.forContractType();
	private String name;
	public FindMechanicsWithContractInForceInContractType(String string) {
		Argument.isNotEmpty(string);
		this.name = string;
	}

	@Override
	public List<MechanicBLDto> execute() throws BusinessException {
		List<MechanicBLDto> list = new ArrayList<MechanicBLDto>();
		for(ContractBLDto dto : getContracts(name)) {
			if(dto.state == ContractState.IN_FORCE)
				list.add(MechanicAssembler.toBLDto(gtw.findById(dto.dni)).get());
		}
		return list;
	}
	
	private List<ContractBLDto> getContracts(String name){
		Optional<ContractTypeDALDto> dto = (ctgtw.findByName(name)) ;
		if(dto.isEmpty())
			return new ArrayList<ContractBLDto>();
		return ContractAssembler.toBLDtoList(cgtw.findByContractTypeId(dto.get().id));
	}

}
