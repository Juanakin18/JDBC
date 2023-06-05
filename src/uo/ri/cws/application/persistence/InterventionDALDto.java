package uo.ri.cws.application.persistence;

import java.time.LocalDateTime;

public class InterventionDALDto {
	public String id;
	public LocalDateTime date;
	public int minutes;
	public long version;
	public double amount;
	public String mechanic_id;
	public String workorder_id;
	
}
