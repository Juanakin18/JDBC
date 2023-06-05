package uo.ri.cws.application.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.business.invoice.crud.commands.SubstitutionAssembler;
import uo.ri.cws.application.business.invoice.crud.commands.SubstitutionDALDto;
import uo.ri.cws.application.business.invoice.crud.commands.SubstitutionGateway;
import uo.ri.cws.application.persistence.util.Conf;

public class SubstitutionGatewayImpl implements SubstitutionGateway {

	@Override
	public void add(SubstitutionDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TSUBSTITUTIONS_ADD"));
		
		pst.setString(1, t.id);
		pst.setInt(2, t.quantity);
		pst.setLong(3,t.version);
		pst.setString(5, t.sparepart_id);
		pst.setString(4, t.intervention_id);
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
			pst = c.prepareStatement(Conf.getInstance().getProperty("TSUBSTITUTIONS_DELETE"));
		
		pst.setString(1, id);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		
	}

	@Override
	public void update(SubstitutionDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TSUBSTITUTIONS_REMOVE"));
		
		pst.setString(5, t.id);
		pst.setInt(1, t.quantity);
		pst.setLong(2,t.version);
		pst.setString(4, t.sparepart_id);
		pst.setString(3, t.intervention_id);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public Optional<SubstitutionDALDto> findById(String id) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TSUBSTITUTIONS_FINDBYID"));
		
		pst.setString(1, id);
		return SubstitutionAssembler.toDALDto(pst.executeQuery());
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<SubstitutionDALDto> findAll() {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TSUBSTITUTIONS_FINDALL"));
		
		return SubstitutionAssembler.toDALDtoList(pst.executeQuery());
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<SubstitutionDALDto> findByIntervention(String id) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TSUBSTITUTIONS_FINDBYINTERVENTION"));
			pst.setString(1, id);
		
		return SubstitutionAssembler.toDALDtoList(pst.executeQuery());
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

}
