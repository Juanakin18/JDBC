package uo.ri.cws.application.ui.foreman.action;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessFactory;

public class DeleteCustomerAction implements Action {

	@Override
	public void execute() throws Exception {
		String nif = Console.readString("NIF");
		
		
		BusinessFactory.forClientService().deleteClient(nif);
		Console.println("Customer deleted");

	}

}
