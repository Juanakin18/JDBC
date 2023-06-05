package uo.ri.cws.application.ui.manager.action;

import menu.BaseMenu;

public class SparePartMenu extends BaseMenu {

	public SparePartMenu() {
		menuOptions = new Object[][] { 
			{"Manager > Spare parts management", null},
			
			{ "Add spare part", 				AddSparePartAction.class }, 
			{ "Update spare part", 	UpdateSparePartAction.class }, 
			{ "Delete spare part", 				DeleteSparePartAction.class }, 
			{ "List spare parts", 				ListSparePartAction.class },
		};
	}

}
