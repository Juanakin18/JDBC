package uo.ri.cws.application.business.workorder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;

/**
 * This service is intended to be used by the Mechanic It follows the ISP
 * principle (@see SOLID principles from RC Martin)
 */
public interface WorkOrderService {

    List<WorkOrderBLDto> findWorkordersByVehicle(String plate) throws BusinessException;
    List<WorkOrderBLDto> findAllWO() throws BusinessException;

    public class WorkOrderBLDto {

        public String id;
        public long version;

        public String vehicleId;
        public String description;
        public LocalDateTime date;
        public double total;
        public String state;

        // might be null
        public String mechanicId;
        public String invoiceId;

    }


	Optional<WorkOrderBLDto> addWorkOrder(WorkOrderBLDto dto) throws BusinessException;
	void deleteWorkOrder(String dto) throws BusinessException;
	void updateWorkOrder(WorkOrderBLDto dto) throws BusinessException;
}
