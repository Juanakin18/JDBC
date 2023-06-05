package uo.ri.cws.application.persistence.mechanic.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.persistence.util.Conf;

public class MechanicGatewayImpl implements MechanicGateway {


	@Override
	public void add(MechanicDALDto t) {
		
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_ADD"));
		
		pst.setString(1, t.id);
		pst.setString(2, t.dni);
		pst.setString(3, t.name);
		pst.setString(4, t.surname);
		pst.setLong(5, t.version);
		
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
			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_DELETE"));
			pst.setString(1, id);
			pst.executeUpdate();
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}

	}

	@Override
	public void update(MechanicDALDto t) {

		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_UPDATE"));
		
		pst.setString(5, t.id);
		pst.setString(1, t.dni);
		pst.setString(2, t.name);
		pst.setString(3, t.surname);
		pst.setLong(4, t.version);
		
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}

	}

	@Override
	public Optional<MechanicDALDto> findById(String id) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		ResultSet rs = null;
		try {
			c=Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_FINDBYID"));
			pst.setString(1, id);
			rs = pst.executeQuery();
			return MechanicAssembler.toMechanicDALDto(rs);
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}finally {
			Jdbc.close(rs,pst);
		}
	}

	@Override
	public List<MechanicDALDto> findAll() {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		ResultSet rs = null;
		try {
			c=Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_FINDALL"));
			
			rs = pst.executeQuery();
			return MechanicAssembler.toMechanicDALDtoList(rs);
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}finally {
			Jdbc.close(rs,pst);
		}
	}

	@Override
	public Optional<MechanicDALDto> findByDni(String dni) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		ResultSet rs = null;
		try {
			c=Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_FINDBYDNI"));
			pst.setString(1, dni);
			
			rs = pst.executeQuery();
			return MechanicAssembler.toMechanicDALDto(rs);
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}finally {
			Jdbc.close(rs,pst);
		}
	}

}
