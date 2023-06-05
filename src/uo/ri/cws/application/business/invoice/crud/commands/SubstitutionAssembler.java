package uo.ri.cws.application.business.invoice.crud.commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubstitutionAssembler {

	public static List<SubstitutionBLDto> toBLDtoList(List<SubstitutionDALDto> list) {
		List<SubstitutionBLDto> list1 = new ArrayList<SubstitutionBLDto>();
		for(SubstitutionDALDto dal : list) {
			SubstitutionBLDto dto = toBLDto(dal);
				list1.add(dto);
		}
		
		return list1;
	}
	public static List<SubstitutionDALDto> toDALDtoList(List<SubstitutionBLDto> list) {
		List<SubstitutionDALDto> list1 = new ArrayList<SubstitutionDALDto>();
		for(SubstitutionBLDto dal : list) {
			SubstitutionDALDto dto = toDALDto(dal);
				list1.add(dto);
		}
		
		return list1;
	}

	public static SubstitutionBLDto toBLDto(SubstitutionDALDto dal) {
		SubstitutionBLDto dto = new SubstitutionBLDto(); 
			dto.id =dal.id;
			dto.quantity = dal.quantity;
			dto.version = dal.version;
			dto.intervention_id= dal.intervention_id;
			dto.sparepart_id = dal.sparepart_id;
		return dto;
	}
	public static SubstitutionDALDto toDALDto(SubstitutionBLDto dal) {
		SubstitutionDALDto dto = new SubstitutionDALDto(); 
			dto.id =dal.id;
			dto.quantity = dal.quantity;
			dto.version = dal.version;
			dto.intervention_id= dal.intervention_id;
			dto.sparepart_id = dal.sparepart_id;
		return dto;
	}

	public static Optional<SubstitutionDALDto> toDALDto(ResultSet rs) throws SQLException {
		SubstitutionDALDto dto = new SubstitutionDALDto(); 
		if(rs.next()) {
			dto.id = rs.getString(1);
			dto.quantity = rs.getInt(2);
			dto.version = rs.getLong(3);
			dto.intervention_id= rs.getString(4);
			dto.sparepart_id = rs.getString(5);
		}
		return Optional.of(dto);
	}

	public static List<SubstitutionDALDto> toDALDtoList(ResultSet rs) throws SQLException {
		List<SubstitutionDALDto> list = new ArrayList<SubstitutionDALDto>();
		while(rs.next()) {
			SubstitutionDALDto dto = new SubstitutionDALDto(); 
				dto.id = rs.getString(1);
				dto.quantity = rs.getInt(2);
				dto.version = rs.getLong(3);
				dto.intervention_id= rs.getString(4);
				dto.sparepart_id = rs.getString(5);
				list.add(dto);
		}
		
		return list;
	}
	

}
