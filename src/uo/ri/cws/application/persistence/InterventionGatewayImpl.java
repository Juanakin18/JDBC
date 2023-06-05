package uo.ri.cws.application.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.business.invoice.crud.commands.InterventionAssembler;
import uo.ri.cws.application.persistence.util.Conf;

public class InterventionGatewayImpl implements InterventionGateway {

	@Override
	public void add(InterventionDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TINTERVENTIONS_ADD"));
		
		pst.setString(1, t.id);
		pst.setDate(2, Date.valueOf(t.date.toLocalDate()));
		pst.setInt(3,t.minutes);
		pst.setLong(4, t.version);
		pst.setString(5, t.mechanic_id);
		pst.setString(6, t.workorder_id);
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
			pst = c.prepareStatement(Conf.getInstance().getProperty("TINTERVENTIONS_DELETE"));
		
		pst.setString(1, id);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}

	}

	@Override
	public void update(InterventionDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TINTERVENTIONS_UPDATE"));
		
		pst.setString(6, t.id);
		pst.setDate(1, Date.valueOf(t.date.toLocalDate()));
		pst.setInt(2,t.minutes);
		pst.setLong(3, t.version);
		pst.setString(4, t.mechanic_id);
		pst.setString(5, t.workorder_id);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}

	}

	@Override
	public Optional<InterventionDALDto> findById(String id) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TINTERVENTIONS_FINDBYID"));
		
		pst.setString(1, id);
		return Optional.of(InterventionAssembler.toDALDto(pst.executeQuery())) ;
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<InterventionDALDto> findAll() {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TINTERVENTIONS_FINDALL"));
		
		return (InterventionAssembler.toDALDtoList(pst.executeQuery())) ;
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<InterventionDALDto> findByWorkOrder(String workorderId) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TINTERVENTIONS_FINDBYWORKORDER"));
			pst.setString(1, workorderId);
		return (InterventionAssembler.toDALDtoList(pst.executeQuery())) ;
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<InterventionDALDto> findByMechanic(String mechId) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TINTERVENTIONS_FINDBYMECHANIC"));
			pst.setString(1, mechId);
		return (InterventionAssembler.toDALDtoList(pst.executeQuery())) ;
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

}
