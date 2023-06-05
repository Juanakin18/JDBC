package uo.ri.cws.application.business.sparepart.crudcommands;

import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.crud.commands.SparePartAssembler;
import uo.ri.cws.application.business.invoice.crud.commands.SparePartBLDto;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.SparePartGateway;

public class AddSparePart implements Command<Void> {

	private SparePartGateway gtw = PersistenceFactory.forSparePart();
	private SparePartBLDto dto;
	public AddSparePart(SparePartBLDto dto) {
		Argument.isNotNull(dto);
		this.dto = dto;
	}
	@Override
	public Void execute() throws BusinessException {
		gtw.add(SparePartAssembler.toDALDto(Optional.of(dto)).get());
		return null;
	}

}
