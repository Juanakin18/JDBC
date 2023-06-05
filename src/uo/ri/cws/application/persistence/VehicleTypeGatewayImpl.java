package uo.ri.cws.application.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.business.invoice.crud.commands.VehicleTypeAssembler;
import uo.ri.cws.application.business.invoice.crud.commands.VehicleTypeDALDto;
import uo.ri.cws.application.business.invoice.crud.commands.VehicleTypeGateway;
import uo.ri.cws.application.persistence.util.Conf;

public class VehicleTypeGatewayImpl implements VehicleTypeGateway {

	@Override
	public void add(VehicleTypeDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TVEHICLETYPES_ADD"));
		
		pst.setString(1, t.id);
		pst.setString(2, t.name);
		pst.setDouble(3,t.pricePerHour);
		pst.setLong(4, t.version);
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
			pst = c.prepareStatement(Conf.getInstance().getProperty("TVEHICLETYPES_DELETE"));
		
		pst.setString(1, id);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}

	}

	@Override
	public void update(VehicleTypeDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TVEHICLETYPES_UPDATE"));
		
		pst.setString(4, t.id);
		pst.setString(1, t.name);
		pst.setDouble(2,t.pricePerHour);
		pst.setLong(3, t.version);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}

	}

	@Override
	public Optional<VehicleTypeDALDto> findById(String id) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TVEHICLETYPES_FINDBYID"));
		
		pst.setString(1, id);
		return VehicleTypeAssembler.toDALDto(pst.executeQuery());
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<VehicleTypeDALDto> findAll() {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TVEHICLETYPES_FINDALL"));
		
		return VehicleTypeAssembler.toDALDtoList(pst.executeQuery());
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public Optional<VehicleTypeDALDto> findByName(String name) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TVEHICLETYPES_FINDBYNAME"));
			pst.setString(1, name);
		return VehicleTypeAssembler.toDALDto(pst.executeQuery());
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

}
