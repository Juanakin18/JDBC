package uo.ri.cws.application.persistence;

import java.time.LocalDate;

import uo.ri.cws.application.business.contract.ContractService.ContractState;

public class ContractDALDto {

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
	public String mechanic_id;
}
