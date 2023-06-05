package uo.ri.cws.application.ui.manager.action;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.sparepart.SparePartService;

public class DeleteSparePartAction implements Action {

	@Override
	public void execute() throws Exception {
		// Get info
				String dni = Console.readString("code"); 
				
			//	MechanicService service = new MechanicServiceImpl();
				SparePartService service = BusinessFactory.forSparePartService();
				service.deleteSparePart(dni);
				// Print result
				Console.println("SparePart removed");

	}

}
