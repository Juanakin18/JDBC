package uo.ri.cws.application.business.client.crudcommands;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.client.ClientService.ClientBLDto;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.client.ClientAssembler;
import uo.ri.cws.application.persistence.client.ClientGateway;

public class FindAllClients implements Command<List<ClientBLDto>> {

	private ClientGateway cgtw = PersistenceFactory.forClient();
	@Override
	public List<ClientBLDto> execute() throws BusinessException {
			return ClientAssembler.toBLDtoList(cgtw.findAll());
	}

} 
