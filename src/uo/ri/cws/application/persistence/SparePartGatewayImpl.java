package uo.ri.cws.application.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.business.invoice.crud.commands.SparePartAssembler;
import uo.ri.cws.application.business.invoice.crud.commands.SparePartDALDto;
import uo.ri.cws.application.persistence.util.Conf;

public class SparePartGatewayImpl implements SparePartGateway {

	@Override
	public void add(SparePartDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TSPAREPARTS_ADD"));
		
		pst.setString(1, t.id);
		pst.setString(2, t.code);
		pst.setString(3,t.description);
		pst.setLong(5, t.version);
		pst.setDouble(4, t.price);
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
			pst = c.prepareStatement(Conf.getInstance().getProperty("TSPAREPARTS_DELETE"));
		
		pst.setString(1, id);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public void update(SparePartDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TSPAREPARTS_UPDATE"));
		
		pst.setString(5, t.id);
		pst.setString(1, t.code);
		pst.setString(2,t.description);
		pst.setLong(4, t.version);
		pst.setDouble(3, t.price);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}

	}

	@Override
	public Optional<SparePartDALDto> findById(String id) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TSPAREPARTS_FINDBYID"));
		
		pst.setString(1, id);
		return SparePartAssembler.toDALDto(pst.executeQuery());
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<SparePartDALDto> findAll() {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TSPAREPARTS_FINDALL"));
		
		return SparePartAssembler.toDALDtoList(pst.executeQuery());
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

}
