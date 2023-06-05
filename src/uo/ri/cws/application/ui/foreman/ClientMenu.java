package uo.ri.cws.application.ui.foreman;

import menu.BaseMenu;
import uo.ri.cws.application.ui.foreman.action.AddCustomerAction;
import uo.ri.cws.application.ui.foreman.action.DeleteCustomerAction;
import uo.ri.cws.application.ui.foreman.action.ListCustomerAction;
import uo.ri.cws.application.ui.foreman.action.UpdateCustomerAction;

public class ClientMenu extends BaseMenu {

	public ClientMenu() {
		menuOptions = new Object[][] { 
			{ "Foreman > Customer management", null },

			{ "Add customer", AddCustomerAction.class }, 
			{ "Update customer", UpdateCustomerAction.class }, 
			{ "Delete customer", DeleteCustomerAction.class }, 
			{ "List customers", ListCustomerAction.class }, 
		};
	}

}
