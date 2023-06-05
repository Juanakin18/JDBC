package uo.ri.cws.application.persistence.payroll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.util.Conf;

public class PayrollGatewayImpl implements PayrollGateway {

	@Override
	public void add(PayrollDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPAYROLLS_ADD"));
		
		pst.setString(1, t.id);
		pst.setDouble(2, t.bonus);
		pst.setDate(3,java.sql.Date.valueOf(t.date.toLocalDate()));
		pst.setDouble(4, t.incometax);
		pst.setDouble(5, t.monthlyWage);
		pst.setDouble(6, t.nic);
		pst.setDouble(7, t.productivityBonus);
		pst.setDouble(8, t.trienniumPayment);
		pst.setLong(9, t.version);
		pst.setString(10, t.contractId);
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
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPAYROLLS_DELETE"));
		pst.setString(1, id);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}

	}

	@Override
	public void update(PayrollDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPAYROLLS_UPDATE"));
		
		pst.setString(1, t.id);
		pst.setDouble(2, t.bonus);
		pst.setObject(3, t.date);
		pst.setDouble(4, t.incometax);
		pst.setDouble(5, t.monthlyWage);
		pst.setDouble(6, t.nic);
		pst.setDouble(7, t.productivityBonus);
		pst.setDouble(8, t.trienniumPayment);
		pst.setLong(7, t.version);
		pst.setString(7, t.contractId);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}

	}

	@Override
	public Optional<PayrollDALDto> findById(String id) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		ResultSet rs = null;
		try {
			c=Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPAYROLLS_FINDBYID"));
			pst.setString(1, id);
			rs = pst.executeQuery();
			return PayrollAssembler.toPayrollDALDto(rs);
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}finally {
			Jdbc.close(rs,pst);
		}
	}

	@Override
	public List<PayrollDALDto> findAll() {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		ResultSet rs = null;
		try {
			c=Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPAYROLLS_FINDALL"));
			rs = pst.executeQuery();
			return PayrollAssembler.toPayrollDALDtoList(rs);
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}finally {
			Jdbc.close(rs,pst);
		}
	}

	@Override
	public void removeAll(List<PayrollDALDto> payrollDALDtoList) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPAYROLLS_DELETE"));
			for(PayrollDALDto dto: payrollDALDtoList) {
				pst.setString(1,dto.id);
				pst.executeUpdate();
			}
			
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		
	}

	@Override
	public List<PayrollDALDto> findForContract(String idContract) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		ResultSet rs = null;
		try {
			c=Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPAYROLLS_FINDBYCONTRACT"));
			pst.setString(1, idContract);
			rs = pst.executeQuery();
			return PayrollAssembler.toPayrollDALDtoList(rs);
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}finally {
			Jdbc.close(rs,pst);
		}
	}

}
