package uo.ri.cws.application.persistence.contracttype;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;



public class ContractTypeAssembler {

	public static Optional<ContractTypeDALDto> toContractTypeDALDto(ResultSet rs) throws SQLException {
		
		if(rs.next()==false)
			return Optional.empty();
		ContractTypeDALDto dt1 = new ContractTypeDALDto();
		dt1.id = rs.getString("id");
		dt1.version = rs.getLong("version");
		dt1.compensationDays = rs.getDouble("compensationDays");
		dt1.name = rs.getString("name");
		return Optional.of(dt1);
	}
	public static ContractTypeBLDto toBLDto(ContractTypeDALDto dto) {
		ContractTypeBLDto result = new ContractTypeBLDto();
		result.id = dto.id;
		result.version=dto.version;
		result.compensationDays = dto.compensationDays;
		result.name = dto.name;
		return result;
		
	}
	public static List<ContractTypeDALDto> toContractTypeDALDtoList(ResultSet rs) throws SQLException {
		List<ContractTypeDALDto> lista = new ArrayList<ContractTypeDALDto>();
		while(rs.next()) {
			ContractTypeDALDto dt1 = new ContractTypeDALDto();
			dt1.id = rs.getString("id");
			dt1.version = rs.getLong("version");
			dt1.compensationDays = rs.getDouble("compensationDays");
			dt1.name = rs.getString("name");
			lista.add(dt1);
		}
		return lista;
	}
	public static ContractTypeDALDto toContractTypeDALDto(ContractTypeBLDto dto) {
		ContractTypeDALDto result = new ContractTypeDALDto();
		result.id = dto.id;
		result.version=dto.version;
		result.compensationDays = dto.compensationDays;
		result.name = dto.name;
		return result;
	}
	public static List<ContractTypeBLDto> toBLDtoList(List<ContractTypeDALDto> findAll) {
		List<ContractTypeBLDto> list = new ArrayList<ContractTypeBLDto>();
		for(ContractTypeDALDto dto : findAll) {
			list.add(toBLDto(dto));
		}
		return list;
	}

}
