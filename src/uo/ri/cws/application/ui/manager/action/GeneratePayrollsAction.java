package uo.ri.cws.application.ui.manager.action;

import menu.Action;
import uo.ri.cws.application.business.BusinessFactory;

public class GeneratePayrollsAction implements Action {

	@Override
	public void execute() throws Exception {
		BusinessFactory.forPayrollService().generatePayrolls();
	}

}
