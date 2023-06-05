package uo.ri.cws.application.business.client.crudcommands;

import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.client.ClientService.ClientBLDto;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.client.ClientAssembler;
import uo.ri.cws.application.persistence.client.ClientGateway;

public class FindClientsById implements Command<Optional<ClientBLDto>> {

	private String idClient; 
	private ClientGateway cgtw = PersistenceFactory.forClient();
	public FindClientsById(String idClient) {
		Argument.isNotEmpty(idClient);
		this.idClient = idClient;
	}

	@Override
	public Optional<ClientBLDto> execute() throws BusinessException {
		return Optional.of( ClientAssembler.toBLDto(cgtw.findById(idClient).get()));
	}
 
}
