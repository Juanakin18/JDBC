package uo.ri.cws.application.business.sparepart;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.crud.commands.SparePartBLDto;
import uo.ri.cws.application.business.sparepart.crudcommands.AddSparePart;
import uo.ri.cws.application.business.sparepart.crudcommands.DeleteSparePart;
import uo.ri.cws.application.business.sparepart.crudcommands.UpdateSparePart;
import uo.ri.cws.application.business.util.command.CommandExecutor;

public class SparePartServiceImpl implements SparePartService {

	private CommandExecutor exe = new CommandExecutor();
	@Override
	public void addSparePart(SparePartBLDto dto) throws BusinessException {
		exe.execute(new AddSparePart(dto));
		 
	}

	@Override
	public void updateSparePart(SparePartBLDto dto) throws BusinessException {
		exe.execute(new UpdateSparePart(dto));
		
	}

	@Override
	public void deleteSparePart(String dni) throws BusinessException {
		exe.execute(new DeleteSparePart(dni));
		
	}

}
