package uo.ri.cws.application.business.invoice.crud.commands;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;

public class FindNotInvoicedWorkOrders implements Command<List<WorkOrderBLDto>> {

	@Override
	public List<WorkOrderBLDto> execute() throws BusinessException {
		
		return null;
	}

}
