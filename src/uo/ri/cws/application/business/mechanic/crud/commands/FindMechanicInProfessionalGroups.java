package uo.ri.cws.application.business.mechanic.crud.commands;

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
import uo.ri.cws.application.persistence.ProfessionalGroupGateway;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class FindMechanicInProfessionalGroups implements Command<List<MechanicBLDto>> {

	private MechanicGateway gtw = PersistenceFactory.forMechanic();
	private ContractGateway cgtw = PersistenceFactory.forContract();
	private ProfessionalGroupGateway pggtw = PersistenceFactory.forProfessionalGroup();
	private String name;
	
	public FindMechanicInProfessionalGroups(String string) {
		Argument.isNotEmpty(string,"No puede ser nulo o vacío");
		this.name = string;
		
	}

	@Override
	public List<MechanicBLDto> execute() throws BusinessException {
		String id = pggtw.findByName(name).get().id;
		List<MechanicBLDto> dtos = new ArrayList<MechanicBLDto>();
		List<ContractBLDto> cdtos = getContractsForProfessionalGroups(id);
		for(ContractBLDto c : cdtos) {
			dtos.add(MechanicAssembler.toBLDto(gtw.findById(c.dni)).get() );
		}
		return dtos;
	}

	private List<ContractBLDto> getContractsForProfessionalGroups(String name2) {
		return ContractAssembler.toBLDtoList(cgtw.findByProfessionalGroupId(name2));
	}

}
