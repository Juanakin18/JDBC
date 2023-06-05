package uo.ri.cws.application.persistence;

import java.time.LocalDateTime;

public class InterventionBLDto {
	public String id;
	public LocalDateTime date;
	public int minutes;
	public long version;
	public String mechanic_id;
	public String workorder_id;

	public InterventionBLDto() {
	}
}