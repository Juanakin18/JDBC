package uo.ri.cws.application.persistence.contracttype;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.util.Conf;

public class ContractTypeGatewayImpl implements ContractTypeGateway {

	@Override
	public void add(ContractTypeDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTTYPES_ADD"));
		
		pst.setString(1, t.id);
		pst.setLong(4, t.version);
		pst.setString(3, t.name);
		pst.setDouble(2, t.compensationDays);
		
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
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTTYPES_DELETE"));
			pst.setString(1, id);
			pst.executeUpdate();
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}
		
	}

	@Override
	public void update(ContractTypeDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTTYPES_UPDATE"));
		
			
			pst.setLong(2, t.version);
			pst.setString(3, t.name);
			pst.setDouble(1, t.compensationDays);
		
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		
	}

	@Override
	public Optional<ContractTypeDALDto> findById(String id) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		ResultSet rs = null;
		try {
			c=Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTTYPES_FINDBYID"));
			pst.setString(1, id);
			rs = pst.executeQuery();
			rs.next();
			return ContractTypeAssembler.toContractTypeDALDto(rs);
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}finally {
			Jdbc.close(rs,pst);
		}
	}

	@Override
	public List<ContractTypeDALDto> findAll() {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		ResultSet rs = null;
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTTYPES_FINDALL"));
			rs = pst.executeQuery();
			return ContractTypeAssembler.toContractTypeDALDtoList(rs);
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}finally {
			Jdbc.close(rs,pst);
		}
	}

	@Override
	public Optional<ContractTypeDALDto> findByName(String name) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection c = null;
		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTTYPES_FINDBYNAME"));
			pst.setString(1, name);
			rs = pst.executeQuery();
			
			return ContractTypeAssembler.toContractTypeDALDto(rs);
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}finally {
			Jdbc.close(rs,pst);
		}
	}

}
