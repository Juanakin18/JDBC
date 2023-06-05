package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.UUID;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway.MechanicDALDto;

public class AddMechanic implements Command<MechanicBLDto>{



	private MechanicBLDto mechanic = null;
	private MechanicGateway mg = PersistenceFactory.forMechanic();
	public AddMechanic(MechanicBLDto dto) {
		validate(dto);
		mechanic=dto;
	}
	private void validate(MechanicBLDto dto) {
		Argument.isNotNull(dto, "El DTO es nulo");
		Argument.isNotEmpty(dto.dni, "DNI nulo");
		Argument.isNotEmpty(dto.name, "Nombre nulo");
		Argument.isNotEmpty(dto.surname, "Apellido nulo");
	}
	public MechanicBLDto execute() throws BusinessException {
		notRepeatedDni(mechanic.dni);
		insertMechanic();
		return mechanic;
	}
	private void insertMechanic() {
		mechanic.id = UUID.randomUUID().toString();
		mechanic.version=1L;
		MechanicDALDto dalDto = MechanicAssembler.toDALDto(mechanic);
		mg.add(dalDto);
	}


	private void notRepeatedDni(String dni) throws BusinessException{
		if(mg.findByDni(dni).isPresent())
			throw new BusinessException("Ya existe ese dni");
	}
	
}
