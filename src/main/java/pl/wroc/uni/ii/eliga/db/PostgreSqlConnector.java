package pl.wroc.uni.ii.eliga.db;

import java.sql.*;

import static java.lang.String.format;

public class PostgreSqlConnector {
  private Connection connection;
  private final String url, user, password;

  public PostgreSqlConnector(String host, int port, String dbName,
      String user, String password) {
    this.user = user;
    this.password = password;
    url = prepareUrl(host, port, dbName);
  }

  public PreparedStatement prepareStatement(String sql) throws SQLException {
    return getConnection().prepareStatement(sql);
  }

  public ResultSet execute(String sql) throws SQLException {
    return getConnection().createStatement().executeQuery(sql);
  }

  public void executeUpdate(String sql) throws SQLException {
    getConnection().createStatement().executeUpdate(sql);
  }

  private Connection getConnection() throws SQLException {
    connectIfNecessary();
    return connection;
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
