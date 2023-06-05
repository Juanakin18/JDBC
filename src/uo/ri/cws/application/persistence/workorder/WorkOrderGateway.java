package uo.ri.cws.application.persistence.workorder;

import java.time.LocalDateTime;
import java.util.List;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway.WorkOrderDALDto;

public interface WorkOrderGateway extends Gateway<WorkOrderDALDto> {
	List<WorkOrderDALDto> findByMechanic(String id);
	List<WorkOrderDALDto> findByVehicleId(String vehicleId);
	List<WorkOrderDALDto> findByInvoice(String id) ;
	List<WorkOrderDALDto> findInvoiced() ;
	List<WorkOrderDALDto> findByDni(String dni);
	
	public class WorkOrderDALDto {
		public String id;
		public Long version;
		
		public Double amount; 
		public LocalDateTime date;
		public String description;
		public String state;
		public String invoice_id;
		public String mechanic_id;
		public String vehicle_id;
		public Boolean usedforvoucher;
	}


}
