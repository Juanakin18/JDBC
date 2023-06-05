package uo.ri.cws.application.ui.mechanic;

import menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.persistence.workorder.assembler.
WorkOrderAssembler;
import uo.ri.cws.application.ui.util.Printer;

public class ListWorkOrderAction implements Action {

	@Override
	public void execute() throws Exception {
		Printer.printInvoicingWorkOrders(WorkOrderAssembler.
				toInvoicingBLDto(BusinessFactory.forWorkOrderService()
						.findAllWO()));
		
	}

}
