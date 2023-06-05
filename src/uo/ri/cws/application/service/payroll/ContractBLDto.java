package uo.ri.cws.application.service.payroll;

import java.time.LocalDate;

import uo.ri.cws.application.business.contract.ContractService.ContractState;

public class ContractBLDto {

	public String id;
	public LocalDate startDate;
	public double annualBaseWage;
	public long version;
	public String contractTypeName;
	public String professionalGroupName;
	public double settlement;
	public ContractState state;
	public String dni;
	public LocalDate endDate;

}
