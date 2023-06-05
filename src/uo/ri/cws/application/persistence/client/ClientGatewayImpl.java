package uo.ri.cws.application.persistence.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.workorder.assembler.ClientDALDto;

public class ClientGatewayImpl implements ClientGateway {

	@Override
	public void add(ClientDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCLIENTS_ADD"));
		
		pst.setString(1, t.id);
		pst.setString(2, t.dni);
		pst.setString(3,t.email);
		pst.setString(4, t.name);
		pst.setString(5, t.phone);
		pst.setString(6, t.surname);
		pst.setLong(7, t.version);
		pst.setString(8, t.city);
		pst.setString(9, t.street);
		pst.setString(10, t.zipcode);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		
	}

	@Override
	public void remove(String id) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCLIENTS_REMOVE"));
		
		pst.setString(1, id);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		
	}

	@Override
	public void update(ClientDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCLIENTS_UPDATE"));
		
		pst.setString(10, t.id);
		pst.setString(1, t.dni);
		pst.setString(2,t.email);
		pst.setString(3, t.name);
		pst.setString(4, t.phone);
		pst.setString(5, t.surname);
		pst.setLong(6, t.version);
		pst.setString(7, t.city);
		pst.setString(8, t.street);
		pst.setString(9, t.zipcode);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		
	}

	@Override
	public Optional<ClientDALDto> findById(String id) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCLIENTS_FINDBYID"));
		
		pst.setString(1, id);
		return Optional.of(ClientAssembler.toDALDto(pst.executeQuery()));
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<ClientDALDto> findAll() {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCLIENTS_FINDALL"));
		
		return ClientAssembler.toDALDtoList(pst.executeQuery());
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public Optional<ClientDALDto> findByDni(String dni) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCLIENTS_FINDBYDNI"));
		
		pst.setString(1, dni);
		ClientDALDto dto = ClientAssembler.toDALDto(pst.executeQuery());
		if(dto!=null)
			return Optional.of(ClientAssembler.toDALDto(pst.executeQuery()));
		else
			return Optional.empty();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

}
