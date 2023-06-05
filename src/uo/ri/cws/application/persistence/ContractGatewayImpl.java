package uo.ri.cws.application.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.business.contract.ContractService.ContractState;
import uo.ri.cws.application.business.payroll.ContractAssembler;
import uo.ri.cws.application.persistence.util.Conf;

public class ContractGatewayImpl implements ContractGateway {

	@Override
	public void add(ContractDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTS_ADD"));
		
		pst.setString(1, t.id);
		pst.setDouble(2, t.annualBaseWage);
		pst.setObject(3, t.endDate);
		pst.setDouble(4, t.settlement);
		pst.setObject(5, t.startDate);
		pst.setString(6, t.state.toString());
		pst.setLong(7, t.version);
		pst.setString(8, t.contractTypeName);
		pst.setString(9, t.mechanic_id);
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
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTS_REMOVE"));
		
		pst.setString(1,id);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}

	}

	@Override
	public void update(ContractDALDto t) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTS_UPDATE"));
		
		pst.setString(9, t.id);
		pst.setDouble(1, t.annualBaseWage);
		pst.setObject(2, t.endDate);
		pst.setDouble(3, t.settlement);
		pst.setObject(4, t.startDate);
		pst.setString(5, t.state.toString());
		pst.setLong(6, t.version);
		pst.setString(7, t.contractTypeName);
		pst.setString(8, t.mechanic_id);
		pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}

	}

	@Override
	public Optional<ContractDALDto> findById(String id) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTS_FINDBYID"));
		pst.setString(1, id);
		return ContractAssembler.toDALDto(pst.executeQuery()) ;
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<ContractDALDto> findAll() {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTS_FINDALL"));
		return ContractAssembler.toDALDtoList(pst.executeQuery()) ;
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<ContractDALDto> findByMechId(String mechId) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTS_FINDBYMECHID"));
			pst.setString(1, mechId);
		return ContractAssembler.toDALDtoList(pst.executeQuery()) ;
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<ContractDALDto> findByMonth(String month) {
		List<ContractDALDto> allDtos = findAll();
		List<ContractDALDto> filtered = new ArrayList<ContractDALDto>();
		for(ContractDALDto dto : allDtos) {
			if(dto.endDate==null) {
				if(dto.state == ContractState.IN_FORCE)
					filtered.add(dto);
			}else if(dto.endDate.isAfter(LocalDate.now()) || dto.endDate.getMonth().getValue() == Month.valueOf(month).getValue()) {
				filtered.add(dto);
			}
		}
		return filtered;
	}

	@Override
	public List<ContractDALDto> findByContractTypeId(String contractTypeName) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTS_FINDBYCONTRACTTYPEID"));
			pst.setString(1, contractTypeName);
		return ContractAssembler.toDALDtoList(pst.executeQuery()) ;
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public List<ContractDALDto> findByProfessionalGroupId(String name2) {
		PreparedStatement pst = null;
		Connection c = Jdbc.getCurrentConnection();
		try {
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTS_FINDBYPROFESSIONALGROUPID"));
			pst.setString(1, name2);
		return ContractAssembler.toDALDtoList(pst.executeQuery()) ;
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	

}
