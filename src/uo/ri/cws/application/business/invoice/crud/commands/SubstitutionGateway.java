package uo.ri.cws.application.business.invoice.crud.commands;

import java.util.List;

import uo.ri.cws.application.persistence.Gateway;
 
public interface SubstitutionGateway extends Gateway<SubstitutionDALDto> {

	List<SubstitutionDALDto> findByIntervention(String id);

}
