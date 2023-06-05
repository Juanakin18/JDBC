package uo.ri.cws.application.persistence.payroll;

import java.util.List;

import uo.ri.cws.application.persistence.Gateway;

public interface PayrollGateway extends Gateway<PayrollDALDto>{

	void removeAll(List<PayrollDALDto> payrollDALDtoList);
	List<PayrollDALDto> findForContract(String idContract);

}
