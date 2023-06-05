package uo.ri.cws.application.persistence.invoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.business.invoice.assembler.InvoicingAssembler;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.util.Conf;

public class InvoiceGatewayImpl implements InvoiceGateway {

	@Override
	public void add(InvoiceDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TINVOICES_ADD"));
		
		pst.setString(1, t.id);
		pst.setString(5, t.state);
		pst.setDate(3, java.sql.Date.valueOf(t.date));
		pst.setDouble(2, t.amount);
		pst.setDouble(6, t.vat);
		pst.setLong(4, t.number);
		pst.setLong(7, t.version);
		
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
			pst = c.prepareStatement(Conf.getInstance().getProperty("TINVOICES_DELETE"));
			pst.setString(1, id);
			pst.executeUpdate();
		}catch(SQLException e) {
			throw new PersistenceException();
		}
		
	}

	@Override
	public void update(InvoiceDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TINVOICES_UPDATE"));
		
		pst.setString(1, t.id);
		pst.setString(2, t.state);
		pst.setObject(3, t.date);
		pst.setDouble(4, t.amount);
		pst.setDouble(5, t.vat);
		pst.setLong(6, t.number);
		pst.setLong(7, t.version);
		
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		
	}

	@Override
	public Optional<InvoiceDALDto> findById(String id) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		ResultSet rs = null;
		try {
			c=Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TINVOICES_FINDBYID"));
			pst.setString(1, id);
			rs = pst.executeQuery();
			
			return InvoicingAssembler.toInvoicingDto(rs);
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}finally {
			Jdbc.close(rs,pst);
		}
	}

	@Override
	public List<InvoiceDALDto> findAll() {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		ResultSet rs = null;
		try {
			c=Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TINVOICES_FINDALL"));
			
			rs = pst.executeQuery();
			return InvoicingAssembler.toInvoicingDALDtoList(rs);
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}finally {
			Jdbc.close(rs,pst);
		}
	}

	@Override
	public Optional<InvoiceDALDto> findByNumber(long number) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		ResultSet rs = null; 
		try {
			c=Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TINVOICES_FINDBYNUMBER"));
			pst.setLong(1, number);
			rs = pst.executeQuery();
			return InvoicingAssembler.toInvoicingDto(rs);
		}catch(SQLException e) {
			throw new PersistenceException(e);
		}finally {
			Jdbc.close(rs,pst);
		}
	}

}
