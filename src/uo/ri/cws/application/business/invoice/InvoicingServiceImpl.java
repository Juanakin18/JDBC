package uo.ri.cws.application.business.invoice;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.assembler.WorkOrderForInvoicingBLDto;
import uo.ri.cws.application.business.invoice.crud.commands.CreateInvoice;
import uo.ri.cws.application.business.invoice.crud.commands.FindInvoiceByNumber;
import uo.ri.cws.application.business.invoice.crud.commands.FindNotInvoicedWorkOrdersByClientDni;
import uo.ri.cws.application.business.invoice.crud.commands.FindPayMeansByClientDni;
import uo.ri.cws.application.business.invoice.crud.commands.FindWorkOrdersByClientDni;
import uo.ri.cws.application.business.invoice.crud.commands.FindWorkOrdersByPlateNumber;
import uo.ri.cws.application.business.paymentmean.PaymentMeanService.PaymentMeanBLDto;
import uo.ri.cws.application.business.util.command.CommandExecutor;
public class InvoicingServiceImpl implements InvoicingService {

	private CommandExecutor executor = new CommandExecutor();
	@Override
	public List<WorkOrderForInvoicingBLDto> findNotInvoicedWorkOrdersByClientDni(String arg) throws BusinessException {
		
		return executor.execute(new FindNotInvoicedWorkOrdersByClientDni(arg));
	}

	@Override
	public Optional<InvoiceBLDto> createInvoiceFor(List<String> workordersIds) throws BusinessException {
		return executor.execute(new CreateInvoice(workordersIds));
	}

	@Override
	public List<WorkOrderForInvoicingBLDto> findWorkOrdersByClientDni(String dni) throws BusinessException {
		
		return executor.execute(new FindWorkOrdersByClientDni(dni));
	}

	@Override
	public List<WorkOrderForInvoicingBLDto> findWorkOrdersByPlateNumber(String plate) throws BusinessException {
		// TODO Auto-generated method stub
		return executor.execute(new FindWorkOrdersByPlateNumber(plate));
	}

	@Override
	public Optional<InvoiceBLDto> findInvoiceByNumber(Long number) throws BusinessException {
		
		return executor.execute(new FindInvoiceByNumber(number));
	}

	@Override
	public List<PaymentMeanForInvoicingBLDto> findPayMeansByClientDni(String dni) throws BusinessException {
		return executor.execute(new FindPayMeansByClientDni(dni));
	}

	@Override
	public void settleInvoice(String invoiceId, List<PaymentMeanBLDto> charges) throws BusinessException {
		executor.execute(new SettleInvoice(invoiceId,charges));
		
	}

}
