package uo.ri.cws.application.business.contracttype.crudcommands;

import java.util.ArrayList;
import java.util.List;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.payroll.ContractAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.ContractGateway;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class FindMechanicByContractType implements Command<List<MechanicBLDto>> {

	private MechanicGateway mgtw = PersistenceFactory.forMechanic();
	private ContractGateway cgtw = PersistenceFactory.forContract();
	private String contractTypeName ;
	public FindMechanicByContractType(String name) {
		Argument.isNotEmpty(name);
		this.contractTypeName = name;
	}

	@Override
	public List<MechanicBLDto> execute() throws BusinessException {
		List<ContractBLDto> contracts = ContractAssembler.toBLDtoList( cgtw.findByContractTypeId(contractTypeName));
		List<MechanicBLDto> mechanics = new ArrayList<MechanicBLDto>();
		for(ContractBLDto c : contracts) {
			mechanics.add(MechanicAssembler.toBLDto(mgtw.findByDni(c.dni)).get()) ;
		}
		return mechanics;
	}

}
