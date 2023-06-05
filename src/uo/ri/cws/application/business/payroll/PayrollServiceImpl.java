package uo.ri.cws.application.business.payroll;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.payroll.crudcommands.DeleteLastPayrollFor;
import uo.ri.cws.application.business.payroll.crudcommands.DeleteLastPayrolls;
import uo.ri.cws.application.business.payroll.crudcommands.GenerateLastMonthPayrolls;
import uo.ri.cws.application.business.payroll.crudcommands.GeneratePayrolls;
import uo.ri.cws.application.business.payroll.crudcommands.GetAllPayrolls;
import uo.ri.cws.application.business.payroll.crudcommands.GetAllPayrollsForMechanic;
import uo.ri.cws.application.business.payroll.crudcommands.GetAllPayrollsForProfessionalGroup;
import uo.ri.cws.application.business.payroll.crudcommands.GetPayrollDetails;
import uo.ri.cws.application.business.util.command.CommandExecutor;
import uo.ri.cws.application.service.payroll.PayrollBLDto;
import uo.ri.cws.application.service.payroll.PayrollSummaryBLDto;

public class PayrollServiceImpl implements PayrollService {

	private CommandExecutor executor = new CommandExecutor();
	@Override
	public void deleteLastPayrollFor(String dni) throws BusinessException {
			executor.execute(new DeleteLastPayrollFor(dni));
	}

	@Override
	public void deleteLastPayrolls() throws BusinessException {
			executor.execute(new DeleteLastPayrolls());
		
	}

	@Override
	public Optional<PayrollBLDto> getPayrollDetails(String string) throws BusinessException {
			return executor.execute(new GetPayrollDetails(string));
	}

	@Override
	public List<PayrollSummaryBLDto> getAllPayrolls() throws BusinessException {
			return executor.execute(new GetAllPayrolls());
	}

	@Override
	public List<PayrollSummaryBLDto> getAllPayrollsForMechanic(String dni) throws BusinessException {
			return executor.execute(new GetAllPayrollsForMechanic( dni));
	}

	@Override
	public List<PayrollSummaryBLDto> getAllPayrollsForProfessionalGroup(String string) throws BusinessException {
		
			return executor.execute(new GetAllPayrollsForProfessionalGroup(string));
	}

	@Override
	public void generatePayrolls(LocalDate currentDate) throws BusinessException {
			executor.execute(new GeneratePayrolls(currentDate));
		
	}

	@Override
	public void generatePayrolls() throws BusinessException {
		executor.execute(new GenerateLastMonthPayrolls());
		
	}

}
