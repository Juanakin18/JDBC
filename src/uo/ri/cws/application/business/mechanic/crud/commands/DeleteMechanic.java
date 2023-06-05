package uo.ri.cws.application.business.mechanic.crud.commands;


import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.ContractGateway;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;

public class DeleteMechanic implements Command<Void>{


	private String mechanic = null;
	private MechanicGateway mg = PersistenceFactory.forMechanic();
	private WorkOrderGateway wg = PersistenceFactory.forWorkOrder();
	private ContractGateway cgtw = PersistenceFactory.forContract();
	public DeleteMechanic(String id) {
		validate(id);
		mechanic=id;
	}
	private void validate(String id) {
		Argument.isNotEmpty(id, "El id es nulo");
	}
	public Void execute() throws BusinessException {

		existingDni(mechanic);
		noWorkorders(mechanic);
		noContracts(mechanic);
		deleteMechanic();
		return null;


	}
	private void noContracts(String mechanic2) throws BusinessException {
		if(cgtw.findByMechId(mechanic2).size()>0)
			throw new BusinessException("Hay contractos para ese mecánico");
		
	}
	private void noWorkorders(String id) throws BusinessException {
		if(wg.findByMechanic(id).size()>0)
			throw new BusinessException("Hay workorders para ese mecánico");
	}
	private void existingDni(String dni) throws  BusinessException {

		if(!mg.findById(dni).isPresent())
			throw new BusinessException("No existe el id");

	}
	private void deleteMechanic(){
		mg.remove(mechanic);
	}


}
