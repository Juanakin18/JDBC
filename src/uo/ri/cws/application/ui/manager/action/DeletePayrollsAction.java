package uo.ri.cws.application.ui.manager.action;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessFactory;

public class DeletePayrollsAction implements Action {

	@Override
	public void execute() throws Exception {
		BusinessFactory.forPayrollService().deleteLastPayrollFor(Console.readString());

	}

}
