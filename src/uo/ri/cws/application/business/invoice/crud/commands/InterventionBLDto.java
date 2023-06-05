package uo.ri.cws.application.business.invoice.crud.commands;

import java.time.LocalDate;

public class InterventionBLDto {
	public String id;
	public LocalDate date;
	public int minutes;
	public long version;
	public String mechanic_id;
	public String workorder_id;
}
