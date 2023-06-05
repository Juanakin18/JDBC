package uo.ri.cws.application.business.payroll.crudcommands;

import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.payroll.PayrollAssembler;
import uo.ri.cws.application.persistence.payroll.PayrollGateway;
import uo.ri.cws.application.service.payroll.PayrollBLDto;
import uo.ri.cws.application.service.payroll.PayrollSummaryBLDto;

public class GetAllPayrolls implements Command<List<PayrollSummaryBLDto>>{

	private PayrollGateway pgtw = PersistenceFactory.forPayroll();
	@Override
	public List<PayrollSummaryBLDto> execute() throws BusinessException {
		
		return toPayrollSummaryBLDto(PayrollAssembler.toBLDtoList( pgtw.findAll()));
	}
	private List<PayrollSummaryBLDto> toPayrollSummaryBLDto(List<PayrollBLDto> findAll) {
		List<PayrollSummaryBLDto> result = new ArrayList<PayrollSummaryBLDto>();
		PayrollSummaryBLDto psdto;
		for(PayrollBLDto dto : findAll) {
			psdto = PayrollAssembler.toPayrollSummaryBLDto(dto);
			result.add(psdto);
		}
		return result;
	}

}
