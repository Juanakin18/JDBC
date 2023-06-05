package uo.ri.cws.application.business.client.crudcommands;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.client.ClientService.ClientBLDto;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.client.ClientAssembler;
import uo.ri.cws.application.persistence.client.ClientGateway;


public class AddClient implements Command<ClientBLDto> {

	private ClientGateway gtw = PersistenceFactory.forClient();
	private ClientBLDto client;
	private String recommenderId;
	public AddClient(ClientBLDto client, String recommenderId) {
		Argument.isNotNull(client);
		Argument.isNotEmpty(recommenderId);
		this.client = client;
		this.recommenderId = recommenderId;
	}

	@Override
	public ClientBLDto execute() throws BusinessException {
		checkNotExists();
		client.id = recommenderId;
		client.version++;
			gtw.add(ClientAssembler.toDALDto(client));
		return client;
	}

	private void checkNotExists() throws BusinessException {
		if(gtw.findByDni(client.dni).isPresent())
			throw new BusinessException("DNI repetido");
		
	}

}
