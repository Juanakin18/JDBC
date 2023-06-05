package uo.ri.cws.application.business.payroll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService.ProfessionalGroupBLDto;
import uo.ri.cws.application.persistence.ProfessionalGroupDALDto;

public class ProfessionalGroupAssembler {

	public static ProfessionalGroupBLDto toBLDto(ProfessionalGroupDALDto dalDto) {
		ProfessionalGroupBLDto dto = new ProfessionalGroupBLDto();
		dto.id = dalDto.id;
		dto.name = dalDto.name;
		dto.productivityRate = dalDto.productivityRate;
		dto.trieniumSalary = dalDto.trieniumSalary;
		dto.version = dalDto.version;
		return dto;
	}

	public static Optional<ProfessionalGroupDALDto> toDALDto(ResultSet rs) throws SQLException {
		ProfessionalGroupDALDto dto = new ProfessionalGroupDALDto();
		if(rs.next()) {
			dto.id = rs.getString(1);
			dto.name = rs.getString(2);
			dto.productivityRate = rs.getDouble(3);
			dto.trieniumSalary = rs.getDouble(4);
			dto.version = rs.getLong(5);
		}
		
		return Optional.of(dto);
	}

	public static List<ProfessionalGroupDALDto> toDALDtoList(ResultSet rs) throws SQLException {
		List<ProfessionalGroupDALDto> result = new ArrayList<ProfessionalGroupDALDto>();
		ProfessionalGroupDALDto dto = new ProfessionalGroupDALDto();
		while(rs.next()) {
			dto = new ProfessionalGroupDALDto();
			dto.id = rs.getString(1);
			dto.name = rs.getString(2);
			dto.productivityRate = rs.getDouble(3);
			dto.trieniumSalary = rs.getDouble(4);
			dto.version = rs.getLong(5);
			result.add(dto);
		}
		return result;
	}

}
