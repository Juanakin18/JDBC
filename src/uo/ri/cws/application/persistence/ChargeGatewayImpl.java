package uo.ri.cws.application.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.business.invoice.ChargeAssembler;
import uo.ri.cws.application.business.invoice.InvoicingService.Charge_BLDto.Charge_DALDto;
import uo.ri.cws.application.persistence.util.Conf;

public class ChargeGatewayImpl implements ChargeGateway {

	@Override
	public void add(Charge_DALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCHARGES_ADD"));
		
		pst.setString(1, t.id);
		pst.setString(4, t.invoice_id);
		pst.setDouble(2,t.amount);
		pst.setLong(3, t.version);
		pst.setString(5, t.paymentMean_id);
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
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCHARGES_REMOVE"));
		
		pst.setString(1, id);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public void update(Charge_DALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCHARGES_UPDATE"));
		
		pst.setString(5, t.id);
		pst.setString(3, t.invoice_id);
		pst.setDouble(1,t.amount);
		pst.setLong(2, t.version);
		pst.setString(4, t.paymentMean_id);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}

	}

	@Override
	public Optional<Charge_DALDto> findById(String id) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCHARGES_FINDBYID"));
		
		pst.setString(1, id);
		return ChargeAssembler.toDALDto(pst.executeQuery());
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<Charge_DALDto> findAll() {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCHARGES_FINDALL"));
		
		return ChargeAssembler.toDALDtoList(pst.executeQuery());
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

}
