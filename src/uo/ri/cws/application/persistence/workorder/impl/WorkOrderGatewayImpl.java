package uo.ri.cws.application.persistence.workorder.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.assembler.WorkOrderAssembler;

public class WorkOrderGatewayImpl implements WorkOrderGateway {
	@Override
	public void add(WorkOrderDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_ADD"));
			pst.setString(1,t.id);
			pst.setDouble(2, t.amount);
			pst.setDate(3,java.sql.Date.valueOf( t.date.toLocalDate()));
			pst.setString(4,t.description);
			pst.setString(5, t.state);
			pst.setLong(6,t.version);
			pst.setString(7, t.invoice_id);
			pst.setString(8, t.mechanic_id);
			pst.setString(9, t.vehicle_id);
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
			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_REMOVE"));
			pst.setString(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public void update(WorkOrderDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_UPDATE"));
			pst.setString(9,t.id);
			pst.setDouble(1, t.amount);
			pst.setDate(2,java.sql.Date.valueOf( t.date.toLocalDate()));
			pst.setString(3,t.description);
			pst.setString(4, t.state);
			pst.setLong(5,t.version);
			pst.setString(6, t.invoice_id);
			pst.setString(7, t.mechanic_id);
			pst.setString(8, t.vehicle_id);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}

	}

	@Override
	public Optional<WorkOrderDALDto> findById(String id) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		ResultSet rs = null;
		try {
			c=Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FINDBYID"));
			pst.setString(1, id);
			
			rs = pst.executeQuery();
			return WorkOrderAssembler.toWorkOrderDALDto(rs);
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}finally {
			Jdbc.close(rs,pst);
		}
	}

	@Override
	public List<WorkOrderDALDto> findAll() {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		ResultSet rs = null;
		try {
			c=Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FINDALL"));
			
			
			rs = pst.executeQuery();
			return WorkOrderAssembler.toWorkOrderDALDtoList(rs);
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}finally {
			Jdbc.close(rs,pst);
		}
	}

	@Override
	public List<WorkOrderDALDto> findByMechanic(String id) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		ResultSet rs = null;
		try {
			c=Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FINDBYDNI"));
			pst.setString(1, id);
			
			rs = pst.executeQuery();
			return WorkOrderAssembler.toWorkOrderDALDtoList(rs);
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}finally {
			Jdbc.close(rs,pst);
		}
	}

	

	@Override
	public List<WorkOrderDALDto> findByVehicleId(String vehicleId) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		ResultSet rs = null;
		try {
			c=Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FINDBYVEHICLE"));
			pst.setString(1, vehicleId);
			rs = pst.executeQuery();
			return WorkOrderAssembler.toWorkOrderDALDtoList(rs);
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}finally {
			Jdbc.close(rs,pst);
		}
	}

	@Override
	public List<WorkOrderDALDto> findByInvoice(String id) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		ResultSet rs = null;
		try {
			c=Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FINDBYINVOICE"));
			rs = pst.executeQuery();
			return WorkOrderAssembler.toWorkOrderDALDtoList(rs);
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}finally {
			Jdbc.close(rs,pst);
		}
	}

	@Override
	public List<WorkOrderDALDto> findInvoiced() {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		ResultSet rs = null;
		try {
			c=Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FINDINVOICED"));
			rs = pst.executeQuery();
			return WorkOrderAssembler.toWorkOrderDALDtoList(rs);
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}finally {
			Jdbc.close(rs,pst);
		}
	}

	

	@Override
	public List<WorkOrderDALDto> findByDni(String dni) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		ResultSet rs = null;
		try {
			c=Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FINDBYDNI"));
			pst.setString(1, dni);
			rs = pst.executeQuery();
			return WorkOrderAssembler.toWorkOrderDALDtoList(rs);
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}finally {
			Jdbc.close(rs,pst);
		}
	}


	


}
