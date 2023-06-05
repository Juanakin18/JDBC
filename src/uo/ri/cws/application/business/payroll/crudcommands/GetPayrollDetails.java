package uo.ri.cws.application.business.payroll.crudcommands;

import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.payroll.PayrollAssembler;
import uo.ri.cws.application.persistence.payroll.PayrollDALDto;
import uo.ri.cws.application.persistence.payroll.PayrollGateway;
import uo.ri.cws.application.service.payroll.PayrollBLDto;

public class GetPayrollDetails implements Command<Optional<PayrollBLDto>> {

	private PayrollGateway pgtw = PersistenceFactory.forPayroll();
	public String id;
	public GetPayrollDetails(String string) {
		Argument.isNotEmpty(string);
		this.id = string;
	}

	@Override
	public Optional<PayrollBLDto> execute() throws BusinessException {
		return PayrollAssembler.toPayrollBLDto(CheckExists(id));
	}

	/**
	 * Comprueba que existe la payroll
	 * @param id2 el id
	 * @throws BusinessException Excepción
	 */
	private Optional<PayrollDALDto> CheckExists(String id2) throws BusinessException {
		Optional<PayrollDALDto> dto = pgtw.findById(id2);
		return dto;
		
	}

}
