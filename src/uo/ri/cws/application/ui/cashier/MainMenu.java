package uo.ri.cws.application.ui.cashier;


import menu.BaseMenu;
import uo.ri.cws.application.ui.cashier.action.FindNotInvoicedWorOrdersByPlateAction;
import uo.ri.cws.application.ui.cashier.action.FindNotInvoicedWorkOrdersAction;
import uo.ri.cws.application.ui.cashier.action.SettleInvoiceAction;
import uo.ri.cws.application.ui.cashier.action.WorkOrdersBillingAction;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Cashier", null },
			{ "Search not invoiced workorders by client",	FindNotInvoicedWorkOrdersAction.class }, 
			{ "Search not invoiced workorders by plate", 	FindNotInvoicedWorOrdersByPlateAction.class }, 
			{ "Workorder billing", 						WorkOrdersBillingAction.class },
			{ "Pay off invoice", 						SettleInvoiceAction.class },
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
