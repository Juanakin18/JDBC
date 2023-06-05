package uo.ri.cws.application.ui.manager.action;


import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicService;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;

public class AddMechanicAction implements Action {

	
	
	@Override
	public void execute() throws BusinessException {
		
		// Get info
		String dni = Console.readString("Dni"); 
		String name = Console.readString("Name"); 
		String surname = Console.readString("Surname");
		
		MechanicBLDto dto = new MechanicBLDto();
		dto.dni=dni;
		dto.name=name;
		dto.surname=surname;
		
	//	MechanicService service = new MechanicServiceImpl();
		MechanicService service = BusinessFactory.forMechanicService();
		service.addMechanic(dto);
		// Print result
		Console.println("Mechanic added");
	}

}
