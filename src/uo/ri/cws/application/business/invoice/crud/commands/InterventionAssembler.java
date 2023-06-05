package uo.ri.cws.application.business.invoice.crud.commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.InterventionDALDto;

public class InterventionAssembler {

	public static InterventionDALDto toDALDto (ResultSet rs) throws SQLException {
		InterventionDALDto dto = new InterventionDALDto();
		if(rs.next()) {
			dto.id = rs.getString(1);
			dto.date = LocalDate.ofInstant( rs.getDate(2).toInstant(), ZoneId.systemDefault()).atStartOfDay();
			dto.minutes = rs.getInt(3);
			dto.version = rs.getLong(4);
			dto.mechanic_id = rs.getString(5);
			dto.workorder_id = rs.getString(6);
		}
		return dto;
	}
	public static List<InterventionBLDto> toBLDtoList(List<InterventionDALDto> dtos) {
		List<InterventionBLDto> list = new ArrayList<InterventionBLDto>();
		for(InterventionDALDto dto : dtos) {
			list.add(toBLDto(dto).get());
		}
		return list;
	}
	public static List<InterventionDALDto> toDALDtoList(List<InterventionBLDto> dtos) {
		List<InterventionDALDto> list = new ArrayList<InterventionDALDto>();
		for(InterventionBLDto dto : dtos) {
			list.add(toDALDto(dto).get());
		}
		return list;
	}
	public static Optional<InterventionBLDto> toBLDto(InterventionDALDto dto1) {
		InterventionBLDto dto = new InterventionBLDto();
			dto.id =dto1.id;
			dto.date = dto1.date.toLocalDate();
			dto.minutes = dto1.minutes;
			dto.version = dto.version;
			dto.mechanic_id = dto.mechanic_id;
			dto.workorder_id = dto.workorder_id;
		return Optional.of(dto);
	}
	public static Optional<InterventionDALDto> toDALDto(InterventionBLDto dto1) {
		InterventionDALDto dto = new InterventionDALDto();
			dto.id =dto1.id;
			dto.date = dto1.date.atStartOfDay();
			dto.minutes = dto1.minutes;
			dto.version = dto.version;
			dto.mechanic_id = dto.mechanic_id;
			dto.workorder_id = dto.workorder_id;
		return Optional.of(dto);
	}
	public static List<InterventionDALDto> toDALDtoList(ResultSet rs) throws SQLException {
		List<InterventionDALDto> list = new ArrayList<InterventionDALDto>();
		while(rs.next()) {
			InterventionDALDto dto = new InterventionDALDto();
			dto.id = rs.getString(1);
			dto.date = LocalDate.ofInstant( rs.getDate(2).toInstant(), ZoneId.systemDefault()).atStartOfDay();
			dto.minutes = rs.getInt(3);
			dto.version = rs.getLong(4);
			dto.mechanic_id = rs.getString(5);
			dto.workorder_id = rs.getString(6);
		}
		return list;
	}

}
