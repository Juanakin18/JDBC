package uo.ri.cws.application.business.client.crudcommands;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.client.ClientService.ClientBLDto;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.client.ClientAssembler;
import uo.ri.cws.application.persistence.client.ClientGateway;

public class UpdateClient implements Command<Void> {
	private ClientGateway gtw = PersistenceFactory.forClient();
	private ClientBLDto client;
	public UpdateClient(ClientBLDto client) {
		Argument.isNotNull(client);
		this.client=client;
	}

	@Override
	public Void execute() throws BusinessException {
		gtw.update(ClientAssembler.toDALDto(client));
		return null;
	}
}