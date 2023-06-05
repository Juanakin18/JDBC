package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.ContractService.ContractState;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.payroll.ContractAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.ContractGateway;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway.MechanicDALDto;

public class FindMechanicsInForce implements Command<List<MechanicBLDto>> {

	private MechanicGateway gtw = PersistenceFactory.forMechanic();
	private ContractGateway cgtw = PersistenceFactory.forContract();
	
	@Override
	public List<MechanicBLDto> execute() throws BusinessException {
		List<MechanicBLDto> list = new ArrayList<MechanicBLDto>();
		for(ContractBLDto dto : getContracts()) {
			if(dto.state == ContractState.IN_FORCE) {
				Optional<MechanicDALDto> dto1 = gtw.findById(dto.dni);
				if(dto1.isPresent()) {
					list.add(MechanicAssembler.toBLDto(dto1).get());
				}
			}
				
		}
		return list;
	}
	
	private List<ContractBLDto> getContracts(){
		return ContractAssembler.toBLDtoList(cgtw.findAll());
	}

	

}
