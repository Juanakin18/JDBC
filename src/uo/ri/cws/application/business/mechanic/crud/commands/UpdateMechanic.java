package uo.ri.cws.application.business.mechanic.crud.commands;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class UpdateMechanic implements Command<MechanicBLDto> {

	private MechanicBLDto dto;
	private MechanicGateway mg = PersistenceFactory.forMechanic();
	public UpdateMechanic(MechanicBLDto mechanic) {
		checkArguments(mechanic);
		this.dto = mechanic;
	}

	private void checkArguments(MechanicBLDto dto) {
		Argument.isNotNull(dto, "El DTO es nulo");
		Argument.isNotEmpty(dto.dni, "DNI nulo");
		Argument.isNotEmpty(dto.name, "Nombre nulo");
		Argument.isNotEmpty(dto.surname, "Apellido nulo");
	}

	@Override
	public MechanicBLDto execute() throws BusinessException {
		checkIfExists();
		mg.update(MechanicAssembler.toDALDto(dto));
		return dto;
	}

	private void checkIfExists() throws BusinessException {
		if(mg.findById(dto.id).isEmpty()) {
			throw new BusinessException("El mecánico no existe");
		}
		
	}

}
