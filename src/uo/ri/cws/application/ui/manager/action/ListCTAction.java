package uo.ri.cws.application.ui.manager.action;

import java.util.List;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.contract.ContractService.ContractSummaryBLDto;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.ui.util.Printer;

public class ListCTAction implements Action {

	@Override
	public void execute() throws Exception {
		
		List<MechanicBLDto> dto = BusinessFactory.forContractTypeService().
				findMechanicByContractType(Console.readString("nombre"));
		Printer.printMechanics(dto);
		Console.println(dto.size());
		double acc =0;
		List<ContractSummaryBLDto> dta = BusinessFactory.forContractService().findAllContracts();
		for(ContractSummaryBLDto d : dta) {
			acc+=d.settlement;
		}
		Console.println(acc);
			
	}

}
