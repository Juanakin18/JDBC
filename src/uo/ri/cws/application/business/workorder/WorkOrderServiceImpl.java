package uo.ri.cws.application.business.workorder;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.CommandExecutor;
import uo.ri.cws.application.business.workorder.crudcommands.AddWorkOrder;
import uo.ri.cws.application.business.workorder.crudcommands.DeleteWorkOrder;
import uo.ri.cws.application.business.workorder.crudcommands.FindAllWorkOrders;
import uo.ri.cws.application.business.workorder.crudcommands.FindWorkOrdersByVehicle;
import uo.ri.cws.application.business.workorder.crudcommands.UpdateWorkOrder;

public class WorkOrderServiceImpl implements WorkOrderService {

	private CommandExecutor exe = new CommandExecutor();
	@Override
	public List<WorkOrderBLDto> findWorkordersByVehicle(String plate) throws BusinessException {
		
		return exe.execute(new FindWorkOrdersByVehicle(plate));
	}
	@Override
	public Optional<WorkOrderBLDto> addWorkOrder(WorkOrderBLDto dto) throws BusinessException {
		
		return exe.execute(new AddWorkOrder(dto));
	}
	@Override
	public void deleteWorkOrder(String dto) throws BusinessException {
		exe.execute(new DeleteWorkOrder(dto));
	}
	@Override
	public void updateWorkOrder(WorkOrderBLDto dto) throws BusinessException {
		exe.execute(new UpdateWorkOrder(dto));
	}
	@Override
	public List<WorkOrderBLDto> findAllWO() throws BusinessException {
		return exe.execute(new FindAllWorkOrders());
	}

}
