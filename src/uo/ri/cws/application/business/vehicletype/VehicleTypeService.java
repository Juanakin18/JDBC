package uo.ri.cws.application.business.vehicletype;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;

/**
 * This service is intended to be used by the Cashier
 * It follows the ISP principle (@see SOLID principles from RC Martin)
 */
public interface VehicleTypeService {

    // ...

    public static class VehicleTypeBLDto {

        public String id;
        public long version;

        public String name;
        public double pricePerHour;
        public int minTrainigHours;

    }

	public void addVehicleType(VehicleTypeBLDto dto) throws BusinessException;

	public void deleteVehicleType(String dni) throws BusinessException;

	public List<VehicleTypeBLDto> findById(String dni) throws BusinessException;

	public void update(VehicleTypeBLDto dto) throws BusinessException;
}
