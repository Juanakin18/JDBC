package uo.ri.cws.application.ui.manager.action;

import java.util.Optional;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.ui.util.Printer;

public class FindMechanicAction implements Action{

	@Override
	public void execute() throws Exception {

			Console.println("\nList of mechanics \n");  
			
			Optional<MechanicBLDto> mechanics = BusinessFactory.forMechanicService().findMechanicByDni(Console.readString());
			Printer.printMechanic(mechanics.get());
		
		
	}

	
}
