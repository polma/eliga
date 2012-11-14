package pl.wroc.uni.ii.eliga.db;

import static java.lang.String.format;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSqlConnector {
  private Connection connection;
  private final String url, user, password;

  public PostgreSqlConnector(String host, int port, String dbName,
      String user, String password) {
    this.user = user;
    this.password = password;
    url = prepareUrl(host, port, dbName);
  }

  public ResultSet execute(String sql) throws SQLException {
    connectIfNecessary();

    Statement statement;
    statement = connection.createStatement();
    return statement.executeQuery(sql);
  }

  private void connectIfNecessary() throws SQLException {
    try {
      if (connection == null || connection.isClosed()) {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url, user, password);
      }
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  private String prepareUrl(String host, int port, String dbName) {
    return format("jdbc:postgresql://%s:%d/%s", host, port, dbName);
  }
}
