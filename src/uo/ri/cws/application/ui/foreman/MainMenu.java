package uo.ri.cws.application.ui.foreman;

import menu.BaseMenu;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Foreman", null },
			{ "Client reception ", 		ClientReceptionMenu.class }, 
			{ "Client management", 		ClientMenu.class },
			{ "Vehicle management", 	VehicleMenu.class },
			{ "Client history review", 	ClientHistorMenu.class }, 
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
