package pl.wroc.uni.ii.eliga.db.postgres;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import pl.wroc.uni.ii.eliga.db.DatabaseConnector;

import java.sql.*;

import static java.lang.String.format;
import static pl.wroc.uni.ii.eliga.common.EligaSettings.*;

@Singleton
public class PostgreSqlConnector implements DatabaseConnector {
  private Connection connection;
  private final String url, user, password;

  @Inject
  public PostgreSqlConnector(
      @Named(DB_HOST_PARAM) String host,
      @Named(DB_PORT_PARAM) int port,
      @Named(DB_NAME_PARAM) String dbName,
      @Named(DB_USER_PARAM) String user,
      @Named(DB_PASS_PARAM) String password) {
    this.user = user;
    this.password = password;
    url = prepareUrl(host, port, dbName);
  }

  @Override
  public PreparedStatement prepareStatement(String sql) throws SQLException {
    return getConnection().prepareStatement(sql);
  }

  @Override
  public ResultSet execute(String sql) throws SQLException {
    return getConnection().createStatement().executeQuery(sql);
  }

  @Override
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
