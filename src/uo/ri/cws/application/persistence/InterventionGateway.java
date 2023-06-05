package uo.ri.cws.application.persistence;

import java.util.List;

public interface InterventionGateway extends Gateway<InterventionDALDto> {
	List<InterventionDALDto> findByWorkOrder(String workorderId);
	List<InterventionDALDto> findByMechanic(String mechId);
}
