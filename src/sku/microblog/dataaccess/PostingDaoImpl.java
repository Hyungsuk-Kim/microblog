package sku.microblog.dataaccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sku.microblog.business.service.PostingDao;

public class PostingDaoImpl implements PostingDao {
	
	private Connection obtainConnection() throws SQLException {
    	return DatabaseUtil.getConnection();
    	//return DatabaseUtil.getConnection();
    }
	
	private void closeResources(Connection connection, Statement stmt, ResultSet rs){
		DatabaseUtil.close(connection, stmt, rs);
	}
	
	private void closeResources(Connection connection, Statement stmt){
		DatabaseUtil.close(connection, stmt);
	}
}
