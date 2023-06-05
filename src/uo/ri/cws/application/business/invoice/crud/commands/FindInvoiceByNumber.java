package uo.ri.cws.application.business.invoice.crud.commands;



import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto;
import uo.ri.cws.application.business.invoice.assembler.InvoicingAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;

public class FindInvoiceByNumber implements Command<Optional<InvoiceBLDto> > {

	private InvoiceGateway igtw = PersistenceFactory.forInvoice();
	private long number;
	public FindInvoiceByNumber(Long number) {
		this.number = number;
	}

	@Override
	public Optional<InvoiceBLDto>  execute() throws BusinessException {
		
		return Optional.of(InvoicingAssembler.toDto(igtw.findByNumber(number).get()) ) ;
	}
 
}
