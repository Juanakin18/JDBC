package uo.ri.cws.application.business.invoice;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoicingService.Charge_BLDto;
import uo.ri.cws.application.business.paymentmean.PaymentMeanService.PaymentMeanBLDto;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.ChargeGateway;
import uo.ri.cws.application.persistence.PaymentMeanAssembler;
import uo.ri.cws.application.persistence.PaymentMeanGateway;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class SettleInvoice implements Command<Void> {

	private ChargeGateway cgtw = PersistenceFactory.forCharge();
	private PaymentMeanGateway pgtw = PersistenceFactory.forPaymentMean();
	private List<Charge_BLDto> charges;
	private List<PaymentMeanBLDto> pagos;
	private String invoiceId;
	public SettleInvoice(String invoiceId, List<PaymentMeanBLDto> charges) {
		this.pagos = charges;
	}

	@Override
	public Void execute() throws BusinessException {

		charges = generateCharges(pagos);
		for(Charge_BLDto c : charges) {
			c.invoice_id=invoiceId;
			cgtw.add(ChargeAssembler.toDALDto(c).get());
		}
		
		return null;
	}

	private List<Charge_BLDto> generateCharges(List<PaymentMeanBLDto> pagos2) {
		for(PaymentMeanBLDto dto : pagos2) {
			Charge_BLDto a = new Charge_BLDto();
			a.paymentMean_id = dto.id;
			charges.add(a);
			pgtw.add(PaymentMeanAssembler.toDALDto( dto));
		}
		
		return null;
	}

}
