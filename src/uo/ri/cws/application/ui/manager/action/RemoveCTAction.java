package uo.ri.cws.application.ui.manager.action;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessFactory;

public class RemoveCTAction implements Action{
	@Override
	public void execute() throws Exception {
		String name = Console.readString("name");

		BusinessFactory.forContractTypeService().deleteContractType(name);
		Console.println("Removed");
	}
}
