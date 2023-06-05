package uo.ri.cws.application.persistence;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.service.util.VehicleDALDto;

public interface VehicleGateway extends Gateway<VehicleDALDto>{

	Optional<VehicleDALDto> findByPlateNumber(String plate);

	List<VehicleDALDto> findByClient(String clientId);

	List<VehicleDALDto> findByMake(String make);

}
