package uo.ri.cws.application.business.client;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.client.crudcommands.AddClient;
import uo.ri.cws.application.business.client.crudcommands.DeleteClient;
import uo.ri.cws.application.business.client.crudcommands.FindAllClients;
import uo.ri.cws.application.business.client.crudcommands.FindClientsById;
import uo.ri.cws.application.business.client.crudcommands.UpdateClient;
import uo.ri.cws.application.business.util.command.CommandExecutor;

public class ClientServiceImpl implements ClientService {

	private CommandExecutor executor = new CommandExecutor();
	@Override
	public ClientBLDto addClient(ClientBLDto client, String recommenderId) throws BusinessException {
		return executor.execute(new AddClient(client, recommenderId));
	}

	@Override
	public void deleteClient(String idClient) throws BusinessException {
		executor.execute(new DeleteClient(idClient));
		
	}

	@Override
	public void updateClient(ClientBLDto client) throws BusinessException {
		executor.execute(new UpdateClient(client));
	}

	@Override
	public List<ClientBLDto> findAllClients() throws BusinessException {
		return executor.execute(new FindAllClients());
	}

	@Override
	public Optional<ClientBLDto> findClientById(String idClient) throws BusinessException {
		return executor.execute(new FindClientsById(idClient));
	}

	@Override
	public List<ClientBLDto> findClientsRecommendedBy(String sponsorID) throws BusinessException {
		return null;
	}

}
