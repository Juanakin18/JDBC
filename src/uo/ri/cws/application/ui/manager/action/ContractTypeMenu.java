package uo.ri.cws.application.ui.manager.action;

import menu.BaseMenu;
import uo.ri.cws.application.ui.manager.MechanicMenu;
import uo.ri.cws.application.ui.manager.PayrollMenu;
import uo.ri.cws.application.ui.manager.VehicleTypeMenu;

public class ContractTypeMenu extends BaseMenu {

	public ContractTypeMenu() {
		menuOptions = new Object[][] { 
			{ "Administrator", null },
			{ "Mechanics management", 			MechanicMenu.class }, 
			{ "Spare parts management", 			SparePartMenu.class },
			{ "Vehicle types management", 	VehicleTypeMenu.class },
			{"Payroll management", PayrollMenu.class},
			{"Contract type management", ContractTypeMenu.class}
		};
	}

}
