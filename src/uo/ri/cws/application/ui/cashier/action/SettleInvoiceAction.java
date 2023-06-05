package uo.ri.cws.application.ui.cashier.action;

import java.util.ArrayList;
import java.util.List;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto;
import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto.InvoiceState;
import uo.ri.cws.application.business.paymentmean.PaymentMeanService.Card_BLDto;
import uo.ri.cws.application.business.paymentmean.PaymentMeanService.Cash_BLDto;
import uo.ri.cws.application.business.paymentmean.PaymentMeanService.PaymentMeanBLDto;
import uo.ri.cws.application.business.paymentmean.voucher.VoucherService.VoucherBLDto;
import uo.ri.cws.application.ui.util.Printer;

public class SettleInvoiceAction implements Action {

	/**
	 * Algorithm:
	 * 
	 *  - Ask user invoice number 
	 *  - Retrieve invoice info
	 *  - Display invoice info
	 *  - Check is unpaid (state <> 'PAID')
	 *  - List payment methods accepted for the customer
	 *  - Ask user to type amount to charge in each payment method
	 *  	Check that sum of amounts equals invoice amount
	 *  - Record payments in the DDBB
	 *  - Increase total for each payment method
	 *  - Decrease money available in coupon if any has been redeemed
	 *  - Finally, mark invoice as paid 
	 *  
	 */
	@Override
	public void execute() throws BusinessException {
		Long invoice = Console.readLong("Invoice number");
		InvoiceBLDto dto = BusinessFactory.forInvoicingService().findInvoiceByNumber(invoice).get();
		Printer.printInvoice(dto); 
		if(dto.state == InvoiceState.NOT_YET_PAID) {
			Console.println("You can pay with cash, vouchers and credit card");
			double total =0;
			double cash=0;
			double vouchers =0;
			double credit =0;
			while(Math.round(total-dto.total*100.0)/100.0!=0) {
				total =0;
				cash = Console.readDouble("cash");
				total+=cash;
				vouchers = Console.readDouble("vouchers");
				total+=vouchers;
				credit = Console.readDouble("credit");
				total+=credit;
			}
			List<PaymentMeanBLDto> dtos = new ArrayList<PaymentMeanBLDto>();
			if(cash>0) {
				Cash_BLDto dto1 = new Cash_BLDto();
				dto1.accumulated = cash;
				dtos.add(dto1);
			}
			if(vouchers>0) {
			VoucherBLDto dto2 = new VoucherBLDto();
			dto2.accumulated = vouchers;
			dtos.add(dto2);
			
			}
			if(credit>0) {
				Card_BLDto dto3 = new Card_BLDto();
				dto3.accumulated = credit;
				dtos.add(dto3);
			}
			BusinessFactory.forInvoicingService().settleInvoice(dto.id, dtos);
		}
	}

}
