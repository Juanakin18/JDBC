package uo.ri.cws.application.ui.manager;

import menu.BaseMenu;
import uo.ri.cws.application.ui.manager.action.AddVehicleTypeAction;
import uo.ri.cws.application.ui.manager.action.DeleteVehicleTypeAction;
import uo.ri.cws.application.ui.manager.action.ListVehicleTypeAction;
import uo.ri.cws.application.ui.manager.action.UpdateVehicleTypeAction;

public class VehicleTypeMenu extends BaseMenu {

	public VehicleTypeMenu() {
		menuOptions = new Object[][] { 
			{"Manager > Vehicle type management", null},

			{ "Add vehicle type ", 				AddVehicleTypeAction.class }, 
			{ "Update vehicle type ", 	UpdateVehicleTypeAction.class }, 
			{ "Delete vehicle type ", 				DeleteVehicleTypeAction.class }, 
			{ "List vehicle types", 				ListVehicleTypeAction.class },
		};
	}

}
