package uo.ri.cws.application.persistence;

import java.util.List;

import uo.ri.cws.application.business.invoice.InvoicingService.PaymentMeanForInvoicingBLDto;

public interface PaymentMeanGateway extends Gateway<PaymentMeanForInvoicingBLDto> {

	List<PaymentMeanForInvoicingBLDto> findByDni(String dni);

}
