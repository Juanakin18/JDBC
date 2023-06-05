package uo.ri.cws.application.business.client.crudcommands;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.client.ClientGateway;

public class DeleteClient implements Command<Void> {

	private ClientGateway cgtw = PersistenceFactory.forClient();
	private String idClient;
	public DeleteClient(String idClient) {
		Argument.isNotEmpty(idClient);
		this.idClient = idClient;
	}

	@Override
	public Void execute() throws BusinessException {
		dniExists();
		cgtw.remove(idClient);
		return null;
	}

	private void dniExists() throws BusinessException {
		if(cgtw.findByDni(idClient).isEmpty())
			throw new BusinessException("No existe ese cliente");
		
	}

}
