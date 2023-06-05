package uo.ri.cws.application.persistence;


import uo.ri.cws.application.business.invoice.crud.commands.SubstitutionGateway;
import uo.ri.cws.application.business.invoice.crud.commands.VehicleTypeGateway;
import uo.ri.cws.application.persistence.client.ClientGateway;
import uo.ri.cws.application.persistence.client.ClientGatewayImpl;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGatewayImpl;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
import uo.ri.cws.application.persistence.invoice.InvoiceGatewayImpl;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.impl.MechanicGatewayImpl;
import uo.ri.cws.application.persistence.payroll.PayrollGateway;
import uo.ri.cws.application.persistence.payroll.PayrollGatewayImpl;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.impl.WorkOrderGatewayImpl;

public class PersistenceFactory {


	public static MechanicGateway forMechanic() {
		return new MechanicGatewayImpl();
	}

	public static WorkOrderGateway forWorkOrder() {
		return new WorkOrderGatewayImpl();
	}

	public static InvoiceGateway forInvoice() {
		return new InvoiceGatewayImpl();
	}
	
	public static ClientGateway forClient() {
		return new ClientGatewayImpl();
	}
	
	public static VehicleGateway forVehicle() {
		return new VehicleGatewayImpl();
	}

	public static PaymentMeanGateway forPaymentMean() {
		return new PaymentMeanGatewayImpl();
	}

	public static ChargeGateway forCharge() {
		return new ChargeGatewayImpl();
	}

	public static ContractTypeGateway forContractType() {
		return new ContractTypeGatewayImpl();
	}
	public static PayrollGateway forPayroll() {
		return new PayrollGatewayImpl();
	}

	public static ContractGateway forContract() {
		return new ContractGatewayImpl();
	}
	
	public static ProfessionalGroupGateway forProfessionalGroup() {
		return new ProfessionalGroupGatewayImpl();
	}

	public static InterventionGateway forIntervention() {
		return new InterventionGatewayImpl();
	}

	public static VehicleTypeGateway forVehicleType() {
		return new VehicleTypeGatewayImpl();
	}

	public static SubstitutionGateway forSubstitution() {
		// TODO Auto-generated method stub
		return new SubstitutionGatewayImpl();
	}

	public static SparePartGateway forSparePart() {
		return new SparePartGatewayImpl();
	}
	
}

