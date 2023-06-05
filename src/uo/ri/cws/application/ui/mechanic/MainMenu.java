package uo.ri.cws.application.ui.mechanic;

import menu.BaseMenu;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Mechanic", null },
			{ "List work orders ", 		ListWorkOrderAction.class }, 
			{ "Add spare part to a repair", 		AddSparePartToRepairAction.class },
			{ "Delete spare part from a repair", 	DeleteSparePartFromRepairAction.class },
			{ "Close work order", 				CloseWorkOrderAction.class },
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
