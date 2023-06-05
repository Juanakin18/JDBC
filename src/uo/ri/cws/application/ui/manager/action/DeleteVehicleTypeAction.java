package uo.ri.cws.application.ui.manager.action;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.vehicletype.VehicleTypeService;

public class DeleteVehicleTypeAction implements Action {

	@Override
	public void execute() throws Exception {
		// Get info
				String dni = Console.readString("name"); 
				
				
				
			//	MechanicService service = new MechanicServiceImpl();
				VehicleTypeService service = BusinessFactory.forVehicleTypeService();
				service.deleteVehicleType(dni);
				// Print result
				Console.println("VT removed");
	}

}
