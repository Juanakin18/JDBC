package uo.ri.cws.application.persistence.invoice;

import java.time.LocalDate;
import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway.InvoiceDALDto;
public interface InvoiceGateway extends Gateway<InvoiceDALDto>{

	public class InvoiceDALDto {

		public String id;
		public long version;
		public LocalDate date;
		public String state;
		public double amount;
		public double vat;
		public long number;

	}

	public Optional<InvoiceDALDto> findByNumber(long number);

}
