package uo.ri.cws.application.ui.manager.action;

import java.util.List;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.ui.util.Printer;

public class FindAllMechanicsInForceAction implements Action {

	@Override
	public void execute() throws Exception {
		Console.println("\nList of mechanics \n");  

		List<MechanicBLDto> mechanics = BusinessFactory.forMechanicService().findMechanicsInForce();
		Printer.printMechanics(mechanics);

	}

}
