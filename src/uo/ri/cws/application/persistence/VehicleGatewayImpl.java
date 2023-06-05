package uo.ri.cws.application.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.vehicle.assembler.VehicleAssembler;
import uo.ri.cws.application.service.util.VehicleDALDto;

public class VehicleGatewayImpl implements VehicleGateway {

	@Override
	public void add(VehicleDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TVEHICLES_ADD"));
		
		pst.setString(1, t.id);
		pst.setString(2, t.make);
		pst.setString(3,t.model);
		pst.setString(4, t.platenumber);
		pst.setLong(5, t.version);
		pst.setString(6, t.client_id);
		pst.setString(7, t.vehicletype_id);
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
			pst = c.prepareStatement(Conf.getInstance().getProperty("TVEHICLES_REMOVE"));
		
		pst.setString(1, id);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		
	}

	@Override
	public void update(VehicleDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TVEHICLES_UPDATE"));
		
		pst.setString(7, t.id);
		pst.setString(1, t.make);
		pst.setString(2,t.model);
		pst.setString(3, t.platenumber);
		pst.setLong(4, t.version);
		pst.setString(5, t.client_id);
		pst.setString(6, t.vehicletype_id);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		
	}

	@Override
	public Optional<VehicleDALDto> findById(String id) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TVEHICLES_FINDBYID"));
		
		pst.setString(1, id);
		return (VehicleAssembler.toVehicleDALDto(pst.executeQuery()));
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<VehicleDALDto> findAll() {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TVEHICLES_FINDALL"));
		
		return (VehicleAssembler.toVehicleDALDtoList(pst.executeQuery()));
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public Optional<VehicleDALDto> findByPlateNumber(String id) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TVEHICLES_FINDBYPLATE"));
		
		pst.setString(1, id);
		return (VehicleAssembler.toVehicleDALDto(pst.executeQuery()));
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<VehicleDALDto> findByClient(String clientId) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TVEHICLES_FINDBYCLIENT"));
			pst.setString(1, clientId);
		return (VehicleAssembler.toVehicleDALDtoList(pst.executeQuery()));
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<VehicleDALDto> findByMake(String make) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TVEHICLES_FINDBYMAKE"));
			pst.setString(1, make);
		return (VehicleAssembler.toVehicleDALDtoList(pst.executeQuery()));
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

}
