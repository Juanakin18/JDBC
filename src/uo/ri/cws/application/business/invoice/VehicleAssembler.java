package uo.ri.cws.application.business.invoice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;
import uo.ri.cws.application.service.util.VehicleDALDto;

public class VehicleAssembler {

	public static List<VehicleDALDto> toVehicleDALDtoList(ResultSet rs) throws SQLException {
		List<VehicleDALDto>  result = new ArrayList<VehicleDALDto> ();
		while ( rs.next() ) {
			VehicleDALDto record = new VehicleDALDto();
			record.version = rs.getLong("version");

			record.id = rs.getString("id");
			record.platenumber = rs.getString("platenumber");
			record.make = rs.getString("make");
			record.model = rs.getString("model");
			record.vehicletype_id = rs.getString("vehicletype_id");			
			record.client_id = rs.getString("client_id");
			result.add(record);
		}
		return result; 
		
	}


	public static Optional<VehicleDALDto> toVehicleDALDto(ResultSet rs) throws SQLException {
		VehicleDALDto record = new VehicleDALDto();
		if (rs.next())
			record.version = rs.getLong("version");

			record.id = rs.getString("id");
			record.platenumber = rs.getString("platenumber");
			record.make = rs.getString("make");
			record.model = rs.getString("model");
			record.vehicletype_id = rs.getString("vehicletype_id");			
			record.client_id = rs.getString("client_id");

		return Optional.ofNullable(record);
	}
	
	

	public static List<VehicleBLDto> toBLDtoList(List<VehicleDALDto> findByClient) {
		List<VehicleBLDto>  result = new ArrayList<VehicleBLDto> ();
		for(VehicleDALDto dal : findByClient) {
			result.add(toBLDto(Optional.of(dal)).get());
		}
		return result; 
		
	}
	public static List<VehicleDALDto> toDALDtoList(List<VehicleBLDto> findByClient) {
		List<VehicleDALDto>  result = new ArrayList<VehicleDALDto> ();
		for(VehicleBLDto dal : findByClient) {
			result.add(toDALDto(Optional.of(dal)).get());
		}
		return result; 
		
	}

	public static Optional<VehicleBLDto> toBLDto(Optional<VehicleDALDto> o) {
		VehicleBLDto record = new VehicleBLDto();
		VehicleDALDto dal = o.get();
		record.version = dal.version;

		record.id = dal.id;
		record.plateNumber = dal.platenumber;
		record.make = dal.make;
		record.model = dal.model;
		record.vehicleTypeId = dal.vehicletype_id;		
		record.clientId =  dal.client_id;
		return Optional.of(record);
	}
	public static Optional<VehicleDALDto> toDALDto(Optional<VehicleBLDto> o) {
		VehicleDALDto record = new VehicleDALDto();
		VehicleBLDto dal = o.get();
		record.version = dal.version;

		record.id = dal.id;
		record.platenumber = dal.plateNumber;
		record.make = dal.make;
		record.model = dal.model;
		record.vehicletype_id = dal.vehicleTypeId;		
		record.client_id =  dal.clientId;
		return Optional.of(record);
	}

}
