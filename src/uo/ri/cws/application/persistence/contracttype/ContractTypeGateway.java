package uo.ri.cws.application.persistence.contracttype;

import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;

public interface ContractTypeGateway extends Gateway<ContractTypeDALDto> {

	Optional<ContractTypeDALDto> findByName(String name);

}
