package uo.ri.cws.application.business.payroll.crudcommands;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.payroll.ContractAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.ContractGateway;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway.MechanicDALDto;
import uo.ri.cws.application.persistence.payroll.PayrollAssembler;
import uo.ri.cws.application.persistence.payroll.PayrollDALDto;
import uo.ri.cws.application.persistence.payroll.PayrollGateway;
import uo.ri.cws.application.service.payroll.PayrollBLDto;

public class DeleteLastPayrollFor implements Command<Void> {

	private PayrollGateway gtw = PersistenceFactory.forPayroll();
	private ContractGateway cgtw = PersistenceFactory.forContract();
	private MechanicGateway mgtw = PersistenceFactory.forMechanic();
	
	private String dni ;
	public DeleteLastPayrollFor(String dni) {
		Argument.isNotEmpty(dni);
		this.dni = dni;
	}

	@Override
	public Void execute() throws BusinessException {
		Optional<MechanicDALDto> dto = mgtw.findByDni(dni);
		if(dto.isEmpty())
			throw new BusinessException("No existe ese dni");
		List<ContractBLDto> dtos = ContractAssembler.toBLDtoList( cgtw.findByMechId(dto.get().id));
		List<PayrollBLDto> pdtos = getPayrollsForContracts(dtos);
		gtw.removeAll(PayrollAssembler.toPayrollDALDtoList(pdtos));
		return null;
	}
	
	/**
	 * Devuelve una lista con las payrolls a borrar de los contratos pedidos
	 * @param cdtos contratos
	 * @return la lista
	 */
	private List<PayrollBLDto> getPayrollsForContracts(List<ContractBLDto> cdtos){
		List<PayrollBLDto> pdtos = new ArrayList<PayrollBLDto>();
		List<PayrollDALDto> listaPorContrato;
		for(ContractBLDto dto : cdtos) {
			listaPorContrato = gtw.findForContract(dto.id);
			for(PayrollDALDto daldto: listaPorContrato) {
				if(daldto.date.getMonth()== LocalDateTime.now().getMonth()
						&& daldto.date.getYear()== LocalDateTime.now().getYear())
					pdtos.add(PayrollAssembler.toPayrollBLDto(daldto).get());
			}
		}
		return pdtos;
	}
	

}
