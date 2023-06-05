package uo.ri.cws.application.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.business.invoice.InvoicingService.PaymentMeanForInvoicingBLDto;
import uo.ri.cws.application.persistence.util.Conf;

public class PaymentMeanGatewayImpl implements PaymentMeanGateway {

	@Override
	public void add(PaymentMeanForInvoicingBLDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPAYMENTMEANS_ADD"));
		
		pst.setString(1, t.id);
		pst.setString(2, t.type);
		pst.setDouble(3,t.accumulated);
		pst.setLong(4, t.version);
		pst.setString(5, t.clientId);
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
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPAYMENTMEANS_DELETE"));
		
		pst.setString(1, id);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public void update(PaymentMeanForInvoicingBLDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPAYMENTMEANS_UPDATE"));
		
		pst.setString(5, t.id);
		pst.setString(1, t.type);
		pst.setDouble(2,t.accumulated);
		pst.setLong(3, t.version);
		pst.setString(4, t.clientId);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public Optional<PaymentMeanForInvoicingBLDto> findById(String id) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPAYMENTMEANS_FINDBYID"));
			pst.setString(1, id);
			return PaymentMeanAssembler.toDALDto(pst.executeQuery());
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<PaymentMeanForInvoicingBLDto> findAll() {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPAYMENTMEANS_FINDALL"));
			return PaymentMeanAssembler.toDALDtoList(pst.executeQuery());
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<PaymentMeanForInvoicingBLDto> findByDni(String dni) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPAYMENTMEANS_FINDBYDNI"));
			pst.setString(1, dni);
			return PaymentMeanAssembler.toDALDtoList(pst.executeQuery());
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

}
