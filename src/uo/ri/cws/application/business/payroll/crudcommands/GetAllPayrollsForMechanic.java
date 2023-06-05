package uo.ri.cws.application.business.payroll.crudcommands;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.payroll.ContractAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.ContractGateway;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.payroll.PayrollAssembler;
import uo.ri.cws.application.persistence.payroll.PayrollDALDto;
import uo.ri.cws.application.persistence.payroll.PayrollGateway;
import uo.ri.cws.application.service.payroll.PayrollBLDto;
import uo.ri.cws.application.service.payroll.PayrollSummaryBLDto;

public class GetAllPayrollsForMechanic implements Command<List<PayrollSummaryBLDto>> {

	private PayrollGateway pgtw = PersistenceFactory.forPayroll();
	private ContractGateway cgtw = PersistenceFactory.forContract();
	private MechanicGateway mgtw = PersistenceFactory.forMechanic();
	private String dni;
	public GetAllPayrollsForMechanic(String dni) {
		Argument.isNotEmpty(dni);
		this.dni = dni;
	}
	
	@Override
	public List<PayrollSummaryBLDto> execute() throws BusinessException {
		CheckMechanic(dni);
		List<PayrollBLDto> pdtos = getPayrollsForContracts(ContractAssembler.toBLDtoList( cgtw.findByMechId(dni)));
		List<PayrollSummaryBLDto> result = toPayrollSummaryBLDto(pdtos);
		return result;
	}
	private void CheckMechanic(String dni2) throws BusinessException {
		if(mgtw.findByDni(dni2).isEmpty()) {
			throw new BusinessException("No existe el mecánico");
		}
		
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
	private List<PayrollBLDto> getPayrollsForContracts(List<ContractBLDto> cdtos){
		List<PayrollBLDto> pdtos = new ArrayList<PayrollBLDto>();
		List<PayrollDALDto> listaPorContrato;
		for(ContractBLDto dto : cdtos) {
			listaPorContrato = pgtw.findForContract(dto.id);
			for(PayrollDALDto daldto: listaPorContrato) {
				if(daldto.date.getMonth()== LocalDateTime.now().getMonth())
					pdtos.add(PayrollAssembler.toPayrollBLDto(daldto).get());
			}
		}
		return pdtos;
	}

	

}
