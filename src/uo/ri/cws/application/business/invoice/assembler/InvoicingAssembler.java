package uo.ri.cws.application.business.invoice.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto;
import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto.InvoiceState;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway.InvoiceDALDto;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway.WorkOrderDALDto;

public class InvoicingAssembler {

	public static InvoiceBLDto toDto(InvoiceDALDto arg) {
		InvoiceBLDto result = new InvoiceBLDto();
		result.id = arg.id;
		result.number = arg.number;
		result.state = InvoiceState.valueOf(arg.state);
		result.date = arg.date;
		result.total = arg.amount;
		result.vat = arg.vat;
		return result;
	}
	

	public static List<WorkOrderForInvoicingBLDto> toInvoicingWorkOrderList(
			List<WorkOrderDALDto> arg) {
		List<WorkOrderForInvoicingBLDto> result = new ArrayList<WorkOrderForInvoicingBLDto>();
		for (WorkOrderDALDto record : arg)
			result.add(toDto(record));
		return result;
	}

	private static WorkOrderForInvoicingBLDto toDto(WorkOrderDALDto record) {
		WorkOrderForInvoicingBLDto dto = new WorkOrderForInvoicingBLDto();
		dto.id = record.id;
		dto.date = record.date;
		dto.description = record.description;
		dto.date = record.date;
		dto.state = record.state;
		dto.total = record.amount;

		return dto;
	}


	public static Optional<InvoiceDALDto> toInvoicingDto(ResultSet rs) throws SQLException {
		InvoiceDALDto result = new InvoiceDALDto();
		if(rs.next()) {
			result.id = rs.getString(1);
		result.number = rs.getLong(2);
		result.state = (rs.getString(3));
		Timestamp sqlDate = rs.getTimestamp( "date");
		result.date = sqlDate.toLocalDateTime().toLocalDate();
		result.amount = rs.getDouble(4);
		result.vat = rs.getDouble(5);
		}
		

		return Optional.of(result);
	}


	public static List<InvoiceDALDto> toInvoicingDALDtoList(ResultSet rs) throws SQLException {
		List<InvoiceDALDto> res = new ArrayList<>();
		while(rs.next()) {
			InvoiceDALDto result = new InvoiceDALDto();
				result.id = rs.getString(1);
			result.number = rs.getLong(2);
			result.state = (rs.getString(3));
			Timestamp sqlDate = rs.getTimestamp("date");
			result.date = sqlDate.toLocalDateTime().toLocalDate();
			result.amount = rs.getDouble(4);
			result.vat = rs.getDouble(5);
			
			res.add( result );
		}

		return res;
	}


	


	public static InvoiceDALDto toDALDto(InvoiceBLDto arg) {
		InvoiceDALDto result = new InvoiceDALDto();
		result.id = arg.id;
		result.number = arg.number;
		result.state = (arg.state).toString();
		result.date = arg.date;
		result.amount =Math.round(arg.total*100.0)/100.0;;
		result.vat = arg.vat;
		return result;
	}


	public static List<InvoiceBLDto> toInvoicingBLDtoList(List<InvoiceDALDto> findAll) {
		List<InvoiceBLDto> blds = new ArrayList<InvoiceBLDto>();
		for(InvoiceDALDto dals : findAll) {
			blds.add(toDto(dals));
		}
		return blds;
	}


}
