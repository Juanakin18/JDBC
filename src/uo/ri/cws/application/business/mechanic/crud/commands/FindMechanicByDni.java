package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class FindMechanicByDni implements Command<Optional<MechanicBLDto>> {

	private String dni;
	public FindMechanicByDni(String dniMechanic) {
		Argument.isNotEmpty(dniMechanic,"el dni no puede ser nulo o vacío");
		dni=dniMechanic;
	}

	private MechanicGateway mg = PersistenceFactory.forMechanic();
	
	public Optional<MechanicBLDto> execute(){
		return findAll();
	}
	private Optional<MechanicBLDto> findAll() {
		return MechanicAssembler.toBLDto(mg.findByDni(dni));
	}

}
