package pl.wroc.uni.ii.eliga.db;

import static org.fest.assertions.Assertions.assertThat;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class PostgreSqlConnectorTest {
  PostgreSqlConnector connector =
      new PostgreSqlConnector("localhost", 5433, "postgres", "postgres", "postgres");

  @Before
  public void cleanUpDatabase() throws SQLException {
    connector.executeUpdate("TRUNCATE TABLE nauczyciele CASCADE");
  }

  @Test
  public void interactsWithDatabase() throws SQLException {
    connector.executeUpdate("INSERT INTO nauczyciele VALUES (DEFAULT, 'Jan', 'Kowalsky')");
    ResultSet result = connector.execute("SELECT * FROM nauczyciele");

    assertThat(result.next()).isTrue();
    assertThat(result.getString(2)).isEqualTo("Jan");
    assertThat(result.getString(3)).isEqualTo("Kowalsky");
    assertThat(result.next()).isFalse();
  }
}
