package uo.ri.cws.application.service.util;

import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class FindMechanicById implements Command<Optional<MechanicBLDto>> {

	private String id; 
	private MechanicGateway gtw = PersistenceFactory.forMechanic();
	public FindMechanicById(String id) {
		Argument.isNotEmpty(id,"El id no puede ser nulo ni vacío");
		this.id = id;
	}


	@Override
	public Optional<MechanicBLDto> execute() throws BusinessException {
		return MechanicAssembler.toBLDto(gtw.findById(id)) ;
	}

}
