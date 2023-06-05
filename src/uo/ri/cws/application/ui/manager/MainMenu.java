package uo.ri.cws.application.ui.manager;

import menu.BaseMenu;
import uo.ri.cws.application.ui.manager.action.AddCTAction;
import uo.ri.cws.application.ui.manager.action.ListCTAction;
import uo.ri.cws.application.ui.manager.action.RemoveCTAction;
import uo.ri.cws.application.ui.manager.action.UpdateCTAction;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Manager > Mechanics management", null },
			{ "Add", 			AddCTAction.class }, 
			{ "Remove", 			RemoveCTAction.class },
			{ "Update", 	UpdateCTAction.class },
			{"List", ListCTAction.class},
			
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
