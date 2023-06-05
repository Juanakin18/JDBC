package uo.ri.cws.application.ui.foreman.action;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.client.ClientService.ClientBLDto;

public class UpdateCustomerAction implements Action {

	@Override
	public void execute() throws Exception {
		String nif = Console.readString("NIF");
		String name = Console.readString("name");
		String surname = Console.readString("surname");
		String addressCity = Console.readString("city");
		String addressStreet = Console.readString("street");
		String addressZipCode= Console.readString("zipcode");
		String telephone = Console.readString("telephone");
		String email = Console.readString("email");
		
		ClientBLDto dto = new ClientBLDto();
		dto.dni = nif;
		dto.name = name;
		dto.surname = surname;
		dto.addressCity = addressCity;
		dto.addressStreet = addressStreet;
		dto.addressZipcode = addressZipCode;
		dto.phone = telephone;
		dto.email = email;
		
		BusinessFactory.forClientService().updateClient(dto);
		Console.println("Customer updated");

	}

}
