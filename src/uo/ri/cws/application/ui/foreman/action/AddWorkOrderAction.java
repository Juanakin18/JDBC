package uo.ri.cws.application.ui.foreman.action;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.workorder.WorkOrderService.WorkOrderBLDto;

public class AddWorkOrderAction implements Action {

	@Override
	public void execute() throws BusinessException {
		String plateNumber = Console.readString("plate number");
		String description = Console.readString("Description");
		
		WorkOrderBLDto dto = new WorkOrderBLDto();
		dto.vehicleId = BusinessFactory.forVehicleService().findVehicleByPlate(plateNumber).get().id;
		dto.description = description;
		dto.state = "Open";
		BusinessFactory.forWorkOrderService().addWorkOrder(dto);
	}

}
