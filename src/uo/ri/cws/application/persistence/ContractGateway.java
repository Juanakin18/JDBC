package uo.ri.cws.application.persistence;

import java.util.List;

public interface ContractGateway extends Gateway<ContractDALDto>{

	List<ContractDALDto> findByMechId(String mechId);
	List<ContractDALDto> findByMonth(String month);
	List<ContractDALDto> findByContractTypeId(String contractTypeName);
	List<ContractDALDto> findByProfessionalGroupId(String name2);
}
