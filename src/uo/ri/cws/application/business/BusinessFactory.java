package uo.ri.cws.application.business;

import uo.ri.conf.ProvidersCrudService;
import uo.ri.conf.SparePartCrudService;
import uo.ri.conf.SparePartReportService;
import uo.ri.conf.SuppliesCrudService;
import uo.ri.cws.application.business.certificate.CertificateService;
import uo.ri.cws.application.business.certificate.CertificateServiceImpl;
import uo.ri.cws.application.business.client.ClientHistoryService;
import uo.ri.cws.application.business.client.ClientHistoryServiceImpl;
import uo.ri.cws.application.business.client.ClientService;
import uo.ri.cws.application.business.client.ClientServiceImpl;
import uo.ri.cws.application.business.contract.ContractService;
import uo.ri.cws.application.business.contracttype.ContractTypeService;
import uo.ri.cws.application.business.contracttype.ContractTypeServiceImpl;
import uo.ri.cws.application.business.invoice.InvoicingService;
import uo.ri.cws.application.business.invoice.InvoicingServiceImpl;
import uo.ri.cws.application.business.invoice.SettleInvoiceService;
import uo.ri.cws.application.business.invoice.SettleInvoiceServiceImpl;
import uo.ri.cws.application.business.mechanic.MechanicService;
import uo.ri.cws.application.business.mechanic.crud.commands.MechanicServiceImpl;
import uo.ri.cws.application.business.payroll.PayrollService;
import uo.ri.cws.application.business.payroll.PayrollServiceImpl;
import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService;
import uo.ri.cws.application.business.sparepart.SparePartService;
import uo.ri.cws.application.business.sparepart.SparePartServiceImpl;
import uo.ri.cws.application.business.vehicle.VehicleService;
import uo.ri.cws.application.business.vehicle.VehicleServiceImpl;
import uo.ri.cws.application.business.vehicletype.VehicleTypeService;
import uo.ri.cws.application.business.vehicletype.VehicleTypeServiceImpl;
import uo.ri.cws.application.business.workorder.WorkOrderService;
import uo.ri.cws.application.business.workorder.WorkOrderServiceImpl;

public class BusinessFactory {


	public static MechanicService forMechanicService() {
		return new MechanicServiceImpl();
	}

	public static InvoicingService forInvoicingService() {
		return new InvoicingServiceImpl();
	}

	public static WorkOrderService forWorkOrderService() {
		return new WorkOrderServiceImpl();
	}

	public static VehicleService forVehicleService() {
		return new VehicleServiceImpl();
	}


	public static CertificateService forCertificateService() {
		return new CertificateServiceImpl();
	}

	public static VehicleTypeService forVehicleTypeService() {
		return new VehicleTypeServiceImpl();
	}


	public static ClientService forClientService() {
		return new ClientServiceImpl();
	}

	public static SparePartService forSparePartService() {
		return new SparePartServiceImpl();
	}

	public static SettleInvoiceService forSettleInvoiceService() {
		return new SettleInvoiceServiceImpl();
	}

	public static ClientHistoryService forClientHistoryService() {
		return new ClientHistoryServiceImpl();
	}

	public static PayrollService forPayrollService() {
		return new PayrollServiceImpl();
	}


	public static SparePartCrudService forSparePartCrudService() {
		return new SparePartCrudServiceImpl();
	}

	

	public static ProvidersCrudService forProvidersService() {
		return new ProvidersCrudServiceImpl();
	}


	public static SuppliesCrudService forSuppliesCrudService() {
		return new SuppliesCrudServiceImpl();
	}

	public static SparePartReportService forSparePartReportService() {
		return new SparePartReportServiceImpl();
	}

	
	public static ContractTypeService forContractTypeService() {
		return new ContractTypeServiceImpl();
	}

	public static ProfessionalGroupService forProfessionalGroupService() {
		
		return null;
	}

	public static ContractService forContractService() {
		// TODO Auto-generated method stub
		return new ContractServiceImpl();
	}

}

