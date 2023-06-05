package uo.ri.cws.application.business.invoice.crud.commands;

import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;

public interface VehicleTypeGateway extends Gateway<VehicleTypeDALDto> {
	Optional<VehicleTypeDALDto> findByName(String name);
}
