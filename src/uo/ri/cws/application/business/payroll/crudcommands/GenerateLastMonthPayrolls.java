package uo.ri.cws.application.business.payroll.crudcommands;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.payroll.ContractAssembler;
import uo.ri.cws.application.business.payroll.ProfessionalGroupAssembler;
import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService.ProfessionalGroupBLDto;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.persistence.ContractGateway;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.ProfessionalGroupGateway;
import uo.ri.cws.application.persistence.payroll.PayrollAssembler;
import uo.ri.cws.application.persistence.payroll.PayrollGateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.assembler.WorkOrderAssembler;
import uo.ri.cws.application.service.payroll.PayrollBLDto;

public class GenerateLastMonthPayrolls implements Command<Void> {

	private ContractGateway cgtw = PersistenceFactory.forContract();
	private PayrollGateway pgtw = PersistenceFactory.forPayroll();
	private ProfessionalGroupGateway pggtw = PersistenceFactory.forProfessionalGroup();
	private WorkOrderGateway wgtw = PersistenceFactory.forWorkOrder();
	

	@Override
	public Void execute() throws BusinessException {
		List<ContractBLDto> contratosEsteMes = ContractAssembler.toBLDtoList(cgtw.findByMonth(LocalDate.now().getMonth().toString()));
		List<PayrollBLDto> aAdd = new ArrayList<PayrollBLDto>();
		for(ContractBLDto cdto : contratosEsteMes) {
			if(checkNotExists(cdto.id)) {
					aAdd.add(createPayroll(cdto));
			}
		}
		addAll(aAdd);
		return null;
	}
	
	/**
	 * Crea la payroll
	 * @param cdto el cont
	 * @return
	 */

	private PayrollBLDto createPayroll(ContractBLDto cdto) {
		PayrollBLDto dto = new PayrollBLDto();
		
		dto.id = UUID.randomUUID().toString();
		dto.date = LocalDate.now();
		dto.version = 1;
		dto.monthlyWage = cdto.annualBaseWage/14;
		if(LocalDate.now().getMonthValue()==6||LocalDate.now().getMonthValue()==12) {
			dto.bonus = dto.monthlyWage;
		}
		setProductivityBonus(dto,cdto);
		if(getYears(cdto)%3==0) {
			dto.trienniumPayment=getTrienniumPayment(cdto)*(getYears(cdto)/3);
		}
		double grossWage = dto.monthlyWage+dto.bonus+dto.productivityBonus+dto.trienniumPayment;
		dto.incomeTax = Math.round(getIncomeTax(cdto.annualBaseWage)*grossWage*100.0)/100.0 ;
		dto.nic = (cdto.annualBaseWage/12)*0.05;
		return dto;
	}

	private double getIncomeTax(double abw) {
		if(abw>=0 && abw<12450)
			return 0.19;
		else if(abw>=12450 && abw<20200)
			return 0.24;
		else if(abw>=20200 && abw<35200)
			return 0.3;
		else if(abw>=35200 && abw<60000)
			return 0.37;
		else if(abw>=60000 && abw<300000)
			return 0.45;
		else if(abw>=300000)
			return 0.47;
		else return -1;
	}

	private double getTrienniumPayment(ContractBLDto cdto) {
		ProfessionalGroupBLDto pgdto = ProfessionalGroupAssembler.toBLDto(pggtw.findById(cdto.professionalGroupName).get());
		return pgdto.trieniumSalary;
	}

	private void setProductivityBonus(PayrollBLDto dto, ContractBLDto cdto) {
		double accumulate =0;
		List<WorkOrderBLDto> dtos = WorkOrderAssembler.toWorkOrderBLDtoList( wgtw.findByDni(cdto.dni));
		
		ProfessionalGroupBLDto pgdto = ProfessionalGroupAssembler.toBLDto(pggtw.findById(cdto.professionalGroupName).get());
		for(WorkOrderBLDto wdto : dtos) {
			accumulate += wdto.total;
		}
		dto.productivityBonus = Math.round(accumulate*pgdto.productivityRate*0.01*100.0)/100.0 ;
	}

	private int getYears(ContractBLDto cdto) {
		int currentYear = LocalDate.now().getYear();
		return currentYear-cdto.startDate.getYear();
	}

	private boolean checkNotExists(String id) {
		List<PayrollBLDto> check = PayrollAssembler.toBLDtoList(pgtw.findForContract(id)) ;
		return check.size()==0;
	}

	private void addAll(List<PayrollBLDto> dtos) {
		for(PayrollBLDto dto: dtos) {
			pgtw.add(PayrollAssembler.toPayrollDALDto(dto).get());
		}
	}
}
