package uo.ri.cws.application.persistence.payroll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.service.payroll.PayrollBLDto;
import uo.ri.cws.application.service.payroll.PayrollSummaryBLDto;

public class PayrollAssembler {

	public static Optional<PayrollDALDto> toPayrollDALDto(ResultSet rs) throws SQLException {
		
		if(rs.next()) {
			PayrollDALDto dto = new PayrollDALDto();
			dto.id = rs.getString(1);
			dto.bonus = rs.getDouble(2);
			dto.date =  rs.getDate(3).toLocalDate().atStartOfDay();
			dto.incometax = rs.getDouble(4);
			dto.monthlyWage = rs.getDouble(5);
			dto.nic = rs.getDouble(6);
			dto.productivityBonus = rs.getDouble(7);
			dto.trienniumPayment = rs.getDouble(8);
			dto.version = rs.getLong(9);
			dto.contractId = rs.getString(10);
			return Optional.of(dto);
		}
		
		return Optional.empty();
	}

	public static Optional<PayrollBLDto> toPayrollBLDto(PayrollDALDto dto){
		PayrollBLDto dto1 = new PayrollBLDto();
		dto1.id = dto.id;
		dto1.bonus = dto.bonus;
		dto1.date = dto.date.toLocalDate();
		dto1.incomeTax = dto.incometax;
		dto1.monthlyWage = dto.monthlyWage;
		dto1.nic = dto.nic;
		dto1.productivityBonus = dto.productivityBonus;
		dto1.trienniumPayment = dto.trienniumPayment;
		dto1.version = dto.version;
		dto1.contractId = dto.contractId;
		return Optional.of(dto1);
	}
	public static Optional<PayrollDALDto> toPayrollDALDto(PayrollBLDto dto){
		PayrollDALDto dto1 = new PayrollDALDto();
		dto1.id = dto.id;
		dto1.bonus = dto.bonus;
		dto1.date = dto.date.atTime(10,15);
		dto1.incometax = dto.incomeTax;
		dto1.monthlyWage = dto.monthlyWage;
		dto1.nic = dto.nic;
		dto1.productivityBonus = dto.productivityBonus;
		dto1.trienniumPayment = dto.trienniumPayment;
		dto1.version = dto.version;
		dto1.contractId = dto.contractId;
		return Optional.of(dto1);
	}
	public static List<PayrollDALDto> toPayrollDALDtoList(ResultSet rs) throws SQLException {
		List<PayrollDALDto> result = new ArrayList<PayrollDALDto>();
		while(rs.next()) {
			
			PayrollDALDto dto = new PayrollDALDto();
			dto.id = rs.getString(1);
			dto.bonus = rs.getDouble(2);
			dto.date =  rs.getDate(3).toLocalDate().atStartOfDay();
			dto.incometax = rs.getDouble(4);
			dto.monthlyWage = rs.getDouble(5);
			dto.nic = rs.getDouble(6);
			dto.productivityBonus = rs.getDouble(7);
			dto.trienniumPayment = rs.getDouble(8);
			dto.version = rs.getLong(9);
			dto.contractId = rs.getString(10);
			result.add(dto);
 		}
		return result;
	}

	public static List<PayrollDALDto> toPayrollDALDtoList(List<PayrollBLDto> dtosRemove) {
		List<PayrollDALDto> result = new ArrayList<PayrollDALDto>();
		for(PayrollBLDto dto : dtosRemove) {
			
			result.add(toPayrollDALDto(dto).get());
		}
		return result;
	}

	public static List<PayrollBLDto> toBLDtoList(List<PayrollDALDto> findAll) {
		List<PayrollBLDto> result = new ArrayList<PayrollBLDto>();
		for(PayrollDALDto dto : findAll) {
			result.add(toPayrollBLDto(dto).get());
		}
		return result;
	}

	public static PayrollSummaryBLDto toPayrollSummaryBLDto(PayrollBLDto dto) {
		PayrollSummaryBLDto pdto = new PayrollSummaryBLDto();
		pdto.netWage = calculateNetWage(dto);
		pdto.date = dto.date.atStartOfDay();
		pdto.id = dto.id;
		pdto.version = dto.version;
		return pdto;
	}

	private static double calculateNetWage(PayrollBLDto dto) {
		double grossWage = dto.bonus+dto.monthlyWage+dto.productivityBonus+dto.trienniumPayment;
		double discounts = dto.incomeTax+dto.nic;
		return grossWage-discounts;
	}

	public static Optional<PayrollBLDto> toPayrollBLDto(Optional<PayrollDALDto> checkExists) {
		if(checkExists.isEmpty())
			return Optional.empty();
		else
			return toPayrollBLDto(checkExists.get());
	}

}
