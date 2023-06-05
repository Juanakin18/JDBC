package uo.ri.cws.application.persistence.workorder.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.invoice.assembler.WorkOrderForInvoicingBLDto;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway.WorkOrderDALDto;
import uo.ri.cws.application.service.util.VehicleDALDto;

public class WorkOrderAssembler {



	public static Optional<WorkOrderDALDto> toWorkOrderDALDto ( ResultSet rs ) throws SQLException {
		WorkOrderDALDto record = null;
		
		if (rs.next()) {
			record = resultSetToWorkOrderDALDto(rs);
			}
		return Optional.ofNullable(record);
		
	}

	public static List<WorkOrderDALDto> toWorkOrderDALDtoList(ResultSet rs) throws SQLException {
		List<WorkOrderDALDto> res = new ArrayList<>();
		while(rs.next()) {
			res.add( resultSetToWorkOrderDALDto(rs)	);
		}
		
		return res;
	}
	
	
	private static WorkOrderDALDto resultSetToWorkOrderDALDto ( ResultSet rs ) throws SQLException {
		WorkOrderDALDto record = new WorkOrderDALDto();
		
		record.id = rs.getString(1);
		record.version = rs.getLong(6);

		record.vehicle_id = rs.getString(9);
		record.description = rs.getString(4);
		record.date =  rs.getTimestamp(3).toLocalDateTime();
		record.amount = rs.getDouble(2);
		record.state = rs.getString(5);
		record.mechanic_id = rs.getString(8);
		record.invoice_id = rs.getString(7);
		
		return record;		
	}

	public static Optional<ClientDALDto> toClientDALDto(ResultSet rs) throws SQLException {
		if (rs.next()) {
			return Optional.of(resultSetToClientDALDto( rs ));
		}
		else 	
			return Optional.ofNullable(null);
	}


	private static ClientDALDto resultSetToClientDALDto(ResultSet rs) throws SQLException {
		ClientDALDto record = new ClientDALDto();
		record.id = rs.getString("id");
		record.version = rs.getLong("version");
		
		record.dni = rs.getString("dni");
		record.name = rs.getString("name");
		record.surname = rs.getString("surname");
		record.phone = rs.getString("phone");
		record.email = rs.getString("email");
		record.street = rs.getString("street");
		record.city = rs.getString("City");
		record.zipcode = rs.getString("Zipcode");
		return record;
	}

	public static List<VehicleDALDto> toVehicleDALDtoList(ResultSet rs) throws SQLException {
		List<VehicleDALDto>  result = new ArrayList<VehicleDALDto> ();
		while ( rs.next() ) {
			
			result.add(resultSetToVehicleDALDto(rs));
		}
		return result;
		
	}


	public static Optional<VehicleDALDto> toVehicleDALDto(ResultSet rs) throws SQLException {
		VehicleDALDto result = null;
		if (rs.next())
			result = resultSetToVehicleDALDto(rs);

		return Optional.ofNullable(result);
	}
	
	private static VehicleDALDto resultSetToVehicleDALDto(ResultSet rs) throws SQLException {
		VehicleDALDto record = new VehicleDALDto();
		record.version = rs.getLong("version");

		record.id = rs.getString("id");
		record.platenumber = rs.getString("platenumber");
		record.make = rs.getString("make");
		record.model = rs.getString("model");
		record.vehicletype_id = rs.getString("vehicletype_id");			
		record.client_id = rs.getString("client_id");	
		return record;
	}

	public static List<WorkOrderBLDto> toWorkOrderBLDtoList(List<WorkOrderDALDto> findByDni) {
		List<WorkOrderBLDto> dtos = new ArrayList<WorkOrderBLDto>();
		for(WorkOrderDALDto dto : findByDni) {
			dtos.add(toWorkOrderBLDto(dto));
		}
		return dtos;
	}
	
	public static List<WorkOrderDALDto> toWorkOrderDALDtoList(List<WorkOrderBLDto> findByDni) {
		List<WorkOrderDALDto> dtos = new ArrayList<WorkOrderDALDto>();
		for(WorkOrderBLDto dto : findByDni) {
			dtos.add(toWorkOrderDALDto(dto));
		} 
		return dtos;
	}


	public static WorkOrderBLDto toWorkOrderBLDto(WorkOrderDALDto dto) {
		WorkOrderBLDto dto1 = new WorkOrderBLDto();
		dto1.id = dto.id;
		dto1.version = dto.version;
		dto1.vehicleId = dto.vehicle_id;
		dto1.description = dto.description;
		dto1.date = dto.date;
		dto1.total = dto.amount;
		dto1.state = dto.state;
		dto1.mechanicId = dto.mechanic_id;
		dto1.invoiceId = dto.invoice_id;
		return dto1;
	}
	public static WorkOrderDALDto toWorkOrderDALDto(WorkOrderBLDto dto) {
		WorkOrderDALDto dto1 = new WorkOrderDALDto();
		dto1.id = dto.id;
		dto1.version = dto.version;
		dto1.vehicle_id = dto.vehicleId;
		dto1.description = dto.description;
		dto1.date = dto.date;
		dto1.amount = dto.total;
		dto1.state = dto.state;
		dto1.mechanic_id = dto.mechanicId;
		dto1.invoice_id = dto.invoiceId;
		return dto1;
	}


	public static WorkOrderDALDto toVehicleDALDto(WorkOrderBLDto dto) {
		WorkOrderDALDto dto1 = new WorkOrderDALDto();
		dto1.id = dto.id;
		dto1.version = dto.version;
		dto1.vehicle_id = dto.vehicleId;
		dto1.description = dto.description;
		dto1.date = dto.date;
		dto1.amount = dto.total;
		dto1.state = dto.state;
		dto1.mechanic_id = dto.mechanicId;
		dto1.invoice_id = dto.invoiceId;
		return dto1;
	}

	public static List<WorkOrderForInvoicingBLDto> toInvoicingBLDtoWithDALD(List<WorkOrderDALDto> findByVehicleId) {
		List<WorkOrderForInvoicingBLDto> dtos = new ArrayList<WorkOrderForInvoicingBLDto>();
		for(WorkOrderDALDto dto : findByVehicleId) {
			dtos.add(toInvoicingBLDto(dto));
		}
		return dtos;
	}

	private static WorkOrderForInvoicingBLDto toInvoicingBLDto(WorkOrderDALDto dto) {
		WorkOrderForInvoicingBLDto dto1 = new WorkOrderForInvoicingBLDto();
		dto1.id = dto.id;
		dto1.description = dto.description;
		dto1.date = dto.date;
		dto1.total = dto.amount;
		dto1.state = dto.state;
		return dto1;
	}

	public static List<WorkOrderForInvoicingBLDto> toInvoicingBLDto(List<WorkOrderBLDto> list) {
		List<WorkOrderForInvoicingBLDto> dtos = new ArrayList<WorkOrderForInvoicingBLDto>();
		List<WorkOrderDALDto> findByVehicleId = toWorkOrderDALDtoList(list);
		for(WorkOrderDALDto dto : findByVehicleId) {
			dtos.add(toInvoicingBLDto(dto));
		}
		return dtos;
	}
	
}
