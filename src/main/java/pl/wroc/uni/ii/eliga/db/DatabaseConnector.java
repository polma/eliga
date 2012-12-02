package pl.wroc.uni.ii.eliga.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseConnector {
  PreparedStatement prepareStatement(String sql) throws SQLException;

  ResultSet execute(String sql) throws SQLException;

  void executeUpdate(String sql) throws SQLException;
}
