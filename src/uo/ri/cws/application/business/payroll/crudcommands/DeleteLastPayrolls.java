package uo.ri.cws.application.business.payroll.crudcommands;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.payroll.PayrollAssembler;
import uo.ri.cws.application.persistence.payroll.PayrollDALDto;
import uo.ri.cws.application.persistence.payroll.PayrollGateway;
import uo.ri.cws.application.service.payroll.PayrollBLDto;

public class DeleteLastPayrolls implements Command<Void> {

	private PayrollGateway gtw = PersistenceFactory.forPayroll();
	@Override
	public Void execute() throws BusinessException {
		List<PayrollDALDto> dtos = gtw.findAll();
		List<PayrollBLDto> dtosRemove = new ArrayList<PayrollBLDto>();
		for(PayrollDALDto dto : dtos) {
			PayrollBLDto dt = PayrollAssembler.toPayrollBLDto(dto).get();
			if(dt.date.getMonth()== LocalDateTime.now().getMonth() 
					&& dt.date.getYear()== LocalDateTime.now().getYear() ) {
				if(!dtosRemove.contains(dt))
					dtosRemove.add(dt);
			}
				
		}
		gtw.removeAll(PayrollAssembler.toPayrollDALDtoList(dtosRemove));
		return null;
	}

}
