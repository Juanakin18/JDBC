package uo.ri.cws.application.business.util.command;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.Jdbc;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.persistence.PersistenceException;

public class CommandExecutor {

	public <T> T execute(Command<T> command) throws BusinessException {
		Connection c = null;
		try {
			try {
				c = Jdbc.createThreadConnection();
				c.setAutoCommit(false);

				T res = command.execute();
				
				c.commit();
				
				return  res;

			} catch (BusinessException e) {
				c.rollback();
				throw e;
			}
		} catch (SQLException | PersistenceException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
		
	}


	

}
