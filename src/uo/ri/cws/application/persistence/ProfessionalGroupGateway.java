package uo.ri.cws.application.persistence;

import java.util.Optional;

public interface ProfessionalGroupGateway extends Gateway<ProfessionalGroupDALDto>{

	Optional<ProfessionalGroupDALDto> findByName(String name);
}
