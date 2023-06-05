package uo.ri.cws.application.persistence.client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.business.client.ClientService.ClientBLDto;
import uo.ri.cws.application.persistence.workorder.assembler.ClientDALDto;

public class ClientAssembler {

	public static ClientDALDto toDALDto(ResultSet pst) throws SQLException {
		ClientDALDto dto = new ClientDALDto();
		if(pst.next()) {
			dto.id=  pst.getString(1);
			dto.dni =  pst.getString(2);
			dto.email =  pst.getString(3);
			dto.name = pst.getString(4);
			dto.phone = pst.getString(5);
			dto.surname = pst.getString(6);
			dto.version = pst.getLong(7);
			dto.city = pst.getString(8);
			dto.street = pst.getString(9);
			dto.zipcode = pst.getString(10);
		}else
			return null;
		
		return dto;
	}
	
	public static ClientBLDto toBLDto( ClientDALDto dto1) {
		ClientBLDto dto = new ClientBLDto();
		dto.id=  dto1.id;
		dto.dni = dto1.dni;
		dto.email = dto1.email;
		dto.name = dto1.name;
		dto.phone = dto1.phone;
		dto.surname = dto1.surname;
		dto.version = dto1.version;
		dto.addressCity = dto1.city;
		dto.addressStreet = dto1.street;
		dto.addressZipcode = dto1.zipcode;
		return dto;
	}
	
	public static List<ClientDALDto> toDALDtoList(ResultSet pst) throws SQLException{
		List<ClientDALDto> result = new ArrayList<ClientDALDto>();
		while(pst.next()) {
			ClientDALDto dto = new ClientDALDto();
			dto.id=  pst.getString(1);
			dto.dni =  pst.getString(2);
			dto.email =  pst.getString(3);
			dto.name = pst.getString(4);
			dto.phone = pst.getString(5);
			dto.surname = pst.getString(6);
			dto.version = pst.getLong(7);
			dto.city = pst.getString(8);
			dto.street = pst.getString(9);
			dto.zipcode = pst.getString(10);
			result.add(dto);
		}
		
		return result;
	}
	public static List<ClientDALDto> toDALDtoList(List<ClientBLDto> dto1){
		List<ClientDALDto> result = new ArrayList<ClientDALDto>();
		for(ClientBLDto dto2 : dto1) {
			result.add(toDALDto(dto2));
		}
		return result;
	}
	public static List<ClientBLDto> toBLDtoList(List<ClientDALDto> dto1){
		List<ClientBLDto> result = new ArrayList<ClientBLDto>();
		for(ClientDALDto dto2 : dto1) {
			result.add(toBLDto(dto2));
		}
		return result;
	}
	public static ClientDALDto toDALDto( ClientBLDto dto1){
		ClientDALDto dto = new ClientDALDto();
		dto.id=  dto1.id;
		dto.dni = dto1.dni;
		dto.email = dto1.email;
		dto.name = dto1.name;
		dto.phone = dto1.phone;
		dto.surname = dto1.surname;
		dto.version = dto1.version;
		dto.city = dto1.addressCity;
		dto.street = dto1.addressStreet;
		dto.zipcode = dto1.addressZipcode;
		return dto;
	}

	

}
