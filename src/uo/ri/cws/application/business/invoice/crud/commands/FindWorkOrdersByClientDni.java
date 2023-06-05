package uo.ri.cws.application.business.invoice.crud.commands;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.assembler.InvoicingAssembler;
import uo.ri.cws.application.business.invoice.assembler.WorkOrderForInvoicingBLDto;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;

public class FindWorkOrdersByClientDni implements Command<List<WorkOrderForInvoicingBLDto>> {

	private WorkOrderGateway gwg = PersistenceFactory.forWorkOrder();
	private String dni;
	public FindWorkOrdersByClientDni(String dni) {
		this.dni =dni;
	}

	@Override
	public List<WorkOrderForInvoicingBLDto> execute() throws BusinessException {
		return InvoicingAssembler.toInvoicingWorkOrderList( gwg.findByDni(dni));
	}

}
