package uo.ri.cws.application.business.invoice.crud.commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



public class SparePartAssembler {

	public static Optional<SparePartBLDto> toBLDto(Optional<SparePartDALDto> dals) {
		SparePartBLDto dto = new SparePartBLDto();
		SparePartDALDto dal = dals.get();
		dto.id = dal.id;
		dto.code = dal.id;
		dto.price = dal.price;
		dto.description = dal.description;
		dto.version = dal.version;
		return Optional.of(dto);
	}
	public static Optional<SparePartDALDto> toDALDto(Optional<SparePartBLDto> dals) {
		SparePartDALDto dto = new SparePartDALDto();
		SparePartBLDto dal = dals.get();
		dto.id = dal.id;
		dto.code = dal.id;
		dto.price = dal.price;
		dto.description = dal.description;
		dto.version = dal.version;
		return Optional.of(dto);
	}


	public static Optional<SparePartDALDto> toDALDto(ResultSet rs) throws SQLException {
		SparePartDALDto dto = new SparePartDALDto();
		if(rs.next()) {
			dto.id = rs.getString(1);
			dto.code = rs.getString(2);
			dto.price = rs.getDouble(4);
			dto.description = rs.getString(3);
			dto.version = rs.getLong(5);
		}
		
		return Optional.of(dto);
	}

	public static List<SparePartDALDto> toDALDtoList(ResultSet rs) throws SQLException {
		List<SparePartDALDto> dtos = new ArrayList<SparePartDALDto>();
		while(rs.next()) {
			SparePartDALDto dto = new SparePartDALDto();
				dto.id = rs.getString(1);
				dto.code = rs.getString(2);
				dto.price = rs.getDouble(4);
				dto.description = rs.getString(3);
				dto.version = rs.getLong(5);
			
			
		}
		return dtos;
	}

}
