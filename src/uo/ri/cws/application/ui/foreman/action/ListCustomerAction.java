package uo.ri.cws.application.ui.foreman.action;

import menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.ui.util.Printer;

public class ListCustomerAction implements Action {

	@Override
	public void execute() throws Exception {
		
		
		Printer.printClients(BusinessFactory.forClientService().findAllClients());

	}

}
