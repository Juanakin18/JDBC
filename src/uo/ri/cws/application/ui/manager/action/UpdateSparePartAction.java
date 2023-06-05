package uo.ri.cws.application.ui.manager.action;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.invoice.crud.commands.SparePartBLDto;
import uo.ri.cws.application.business.sparepart.SparePartService;

public class UpdateSparePartAction implements Action {

	@Override
	public void execute() throws Exception {

		// Get info
		String dni = Console.readString("code"); 
		String name = Console.readString("description"); 
		double surname = Console.readDouble("price");
		
		SparePartBLDto dto = new SparePartBLDto();
		dto.code=dni;
		dto.description=name;
		dto.price=surname;
		
	//	MechanicService service = new MechanicServiceImpl();
		SparePartService service = BusinessFactory.forSparePartService();
		service.updateSparePart(dto);
		// Print result
		Console.println("SparePart updated");

	}

}
