package uo.ri.cws.application.ui.manager.action;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;

public class AddCTAction implements Action {

	@Override
	public void execute() throws Exception {
		String name = Console.readString("name");
		double compensation = Console.readDouble("days");

		ContractTypeBLDto dt = new ContractTypeBLDto();
		dt.name = name;
		dt.compensationDays = compensation;
		BusinessFactory.forContractTypeService().addContractType(dt);
		Console.println("Added");
	}

}
