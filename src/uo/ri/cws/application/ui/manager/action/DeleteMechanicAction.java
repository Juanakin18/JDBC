package uo.ri.cws.application.ui.manager.action;


import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicService;

public class DeleteMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		// Get info
		String dni = Console.readString("Dni"); 
				
		//	MechanicService service = new MechanicServiceImpl();
		MechanicService service = BusinessFactory.forMechanicService();
		service.deleteMechanic(dni);
				
		Console.println("Mechanic deleted");
	}

}
