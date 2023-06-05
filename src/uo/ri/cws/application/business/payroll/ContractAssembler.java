package uo.ri.cws.application.business.payroll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.ContractService.ContractState;
import uo.ri.cws.application.persistence.ContractDALDto;

public class ContractAssembler {

	public static Optional<ContractDALDto> toDALDto(ResultSet rs) throws SQLException{
		ContractDALDto bldto = new ContractDALDto();
		bldto.id = rs.getString(1);
		bldto.startDate = rs.getDate(5).toLocalDate();
		bldto.annualBaseWage = rs.getDouble(2);
		bldto.version = rs.getLong(7);
		bldto.contractTypeName = rs.getString(8);
		bldto.professionalGroupName = rs.getString(10);
		bldto.settlement = rs.getDouble(4);
		bldto.state = ContractState.valueOf(rs.getString(6)); 
		bldto.dni =  rs.getString(9);
		if(rs.getDate(3)!=null)
			bldto.endDate = rs.getDate(3).toLocalDate();
		return Optional.of(bldto);
	}
	public static List<ContractDALDto> toDALDtoList(ResultSet rs) throws SQLException{
		List<ContractDALDto> dtos = new ArrayList<ContractDALDto>();
		while(rs.next()) {
			dtos.add(toDALDto(rs).get());
		}
		return dtos;
	}
	public static List<ContractDALDto> toDALDtoList(List<ContractBLDto> bldtos){
		List<ContractDALDto> dtos = new ArrayList<ContractDALDto>();
		for(ContractBLDto dto : bldtos) {
			dtos.add(toDALDto(Optional.of(dto)).get());
		}
		return dtos;
	}
	public static Optional<ContractBLDto> toBLDto(Optional<ContractDALDto> dto){
		ContractBLDto bldto = new ContractBLDto();
		bldto.id = dto.get().id;
		bldto.startDate = dto.get().startDate;
		bldto.annualBaseWage = dto.get().annualBaseWage;
		bldto.version = dto.get().version;
		bldto.contractTypeName = dto.get().contractTypeName;
		bldto.professionalGroupName = dto.get().professionalGroupName;
		bldto.settlement = dto.get().settlement;
		bldto.state = dto.get().state;
		bldto.dni = dto.get().dni;
		bldto.endDate = dto.get().endDate;
		return Optional.of(bldto);
	}
	public static Optional<ContractDALDto> toDALDto(Optional<ContractBLDto> dto){
		ContractDALDto bldto = new ContractDALDto();
		bldto.id = dto.get().id;
		bldto.startDate = dto.get().startDate;
		bldto.annualBaseWage = dto.get().annualBaseWage;
		bldto.version = dto.get().version;
		bldto.contractTypeName = dto.get().contractTypeName;
		bldto.professionalGroupName = dto.get().professionalGroupName;
		bldto.settlement = dto.get().settlement;
		bldto.state = dto.get().state;
		bldto.dni = dto.get().dni;
		bldto.endDate = dto.get().endDate;
		return Optional.of(bldto);
	}
	public static List<ContractBLDto> toBLDtoList(List<ContractDALDto> daldtos) {
		List<ContractBLDto> dtos = new ArrayList<ContractBLDto>();
		for(ContractDALDto dto : daldtos) {
			dtos.add(toBLDto(Optional.of(dto)).get());
		}
		return dtos;
	}

}
