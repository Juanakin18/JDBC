package uo.ri.cws.application.business.sparepart;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.crud.commands.SparePartBLDto;

public interface SparePartService {

	void addSparePart(SparePartBLDto dto) throws BusinessException;

	void updateSparePart(SparePartBLDto dto) throws BusinessException;

	void deleteSparePart(String dni) throws BusinessException;

}
