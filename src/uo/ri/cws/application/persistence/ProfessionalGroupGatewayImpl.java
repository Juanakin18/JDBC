package uo.ri.cws.application.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.business.payroll.ProfessionalGroupAssembler;
import uo.ri.cws.application.persistence.util.Conf;

public class ProfessionalGroupGatewayImpl implements ProfessionalGroupGateway {

	@Override
	public void add(ProfessionalGroupDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPROFESSIONALGROUP_ADD"));
		
		pst.setString(1, t.id);
		pst.setString(2, t.name);
		pst.setDouble(3, t.productivityRate);
		pst.setDouble(4, t.trieniumSalary);
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
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPROFESSIONALGROUP_REMOVE"));
		
		pst.setString(1, id);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public void update(ProfessionalGroupDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPROFESSIONALGROUP_UPDATE"));
		
		pst.setString(1, t.id);
		pst.setString(2, t.name);
		pst.setDouble(3, t.productivityRate);
		pst.setDouble(4, t.trieniumSalary);
		pst.setLong(5, t.version);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}

	}

	@Override
	public Optional<ProfessionalGroupDALDto> findById(String id) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		ResultSet rs = null;
		try {
			c=Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPROFESSIONALGROUPS_FINDBYID"));
			pst.setString(1, id);
			rs = pst.executeQuery();
			return ProfessionalGroupAssembler.toDALDto(rs);
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}finally {
			Jdbc.close(rs,pst);
		}
	}

	@Override
	public List<ProfessionalGroupDALDto> findAll() {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		ResultSet rs = null;
		try {
			c=Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPROFESSIONALGROUPS_FINDALL"));
			
			rs = pst.executeQuery();
			return ProfessionalGroupAssembler.toDALDtoList(rs);
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}finally {
			Jdbc.close(rs,pst);
		}
	}

	@Override
	public Optional<ProfessionalGroupDALDto> findByName(String name) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		ResultSet rs = null;
		try {
			c=Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPROFESSIONALGROUPS_FINDBYNAME"));
			pst.setString(1, name);
			rs = pst.executeQuery();
			return ProfessionalGroupAssembler.toDALDto(rs);
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}finally {
			Jdbc.close(rs,pst);
		}
	}

}
