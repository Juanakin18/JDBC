package uo.ri.cws.application.business.vehicle.crudcommands;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.VehicleAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.VehicleGateway;
import uo.ri.cws.application.persistence.client.ClientGateway;

public class FindByClientDNI implements Command<List<VehicleBLDto>> {

	private ClientGateway cgtw = PersistenceFactory.forClient();
	private VehicleGateway vgtw = PersistenceFactory.forVehicle();
	private String dni;
	public FindByClientDNI(String dni) {
		this.dni = dni;
	}

	@Override
	public List<VehicleBLDto> execute() throws BusinessException {
		return VehicleAssembler.toBLDtoList(vgtw.findByClient(cgtw.findByDni(dni).get().id)) ;
	}

} 
