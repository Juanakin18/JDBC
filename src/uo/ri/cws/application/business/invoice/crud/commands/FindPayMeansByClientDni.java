package uo.ri.cws.application.business.invoice.crud.commands;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoicingService.PaymentMeanForInvoicingBLDto;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PaymentMeanGateway;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class FindPayMeansByClientDni implements Command<List<PaymentMeanForInvoicingBLDto>> {

	private PaymentMeanGateway gtw = PersistenceFactory.forPaymentMean();
	private String dni;
	public FindPayMeansByClientDni(String dni) {
		this.dni = dni;
	}

	@Override
	public List<PaymentMeanForInvoicingBLDto> execute() throws BusinessException {
		// TODO Auto-generated method stub
		return gtw.findByDni(dni);
	} 

}
