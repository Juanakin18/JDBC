package uo.ri.cws.application.persistence.client;

import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.workorder.assembler.ClientDALDto;

public interface ClientGateway extends Gateway<ClientDALDto>{
	Optional<ClientDALDto> findByDni(String dni);
}
