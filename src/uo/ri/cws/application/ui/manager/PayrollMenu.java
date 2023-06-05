package uo.ri.cws.application.ui.manager;

import menu.BaseMenu;
import uo.ri.cws.application.ui.manager.action.DeletePayrollsAction;
import uo.ri.cws.application.ui.manager.action.DeletePayrollsMonthAction;
import uo.ri.cws.application.ui.manager.action.GeneratePayrollsAction;
import uo.ri.cws.application.ui.manager.action.ShowPayrollsAction;

public class PayrollMenu  extends BaseMenu{

	public PayrollMenu() {
	menuOptions = new Object[][] { 
		{"Manager > Payroll management", null},

		{ "Generate payrolls ", 				GeneratePayrollsAction.class }, 
		{ "Delete Payrolls ", 	DeletePayrollsAction.class }, 
		{ "Delete Payrolls This month ", 	DeletePayrollsMonthAction.class }, 
		{ "Show Payrolls ", 				ShowPayrollsAction.class }, 
	};
	}
}
