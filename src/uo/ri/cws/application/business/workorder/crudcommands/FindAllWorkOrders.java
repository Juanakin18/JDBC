package uo.ri.cws.application.business.workorder.crudcommands;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.assembler.WorkOrderAssembler;

public class FindAllWorkOrders implements Command<List<WorkOrderBLDto>> {

	private WorkOrderGateway gtw = PersistenceFactory.forWorkOrder();
	@Override
	public List<WorkOrderBLDto> execute() throws BusinessException {
		return WorkOrderAssembler.toWorkOrderBLDtoList(gtw.findAll());
	}
 
}
