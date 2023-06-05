package uo.ri.cws.application.ui.manager.action;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.vehicletype.VehicleTypeService;

public class UpdateVehicleTypeAction implements Action {

	@Override
	public void execute() throws Exception {
		// Get info
		String id = Console.readString("id");
				String dni = Console.readString("name"); 
				double name = Console.readDouble("price"); 
				
				
				VehicleTypeService.VehicleTypeBLDto dto = new VehicleTypeService.VehicleTypeBLDto();
				dto.id = id;
				dto.name=dni;
				dto.pricePerHour=name;
				
			//	MechanicService service = new MechanicServiceImpl();
				VehicleTypeService service = BusinessFactory.forVehicleTypeService();
				service.update(dto);
				// Print result
				Console.println("VT updated");

	}

}
