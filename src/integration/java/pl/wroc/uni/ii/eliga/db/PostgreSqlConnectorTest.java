package pl.wroc.uni.ii.eliga.db;

import static org.fest.assertions.Assertions.assertThat;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class PostgreSqlConnectorTest {
  @Test
  public void interactsWithDatabase() throws SQLException {
    PostgreSqlConnector connector =
        new PostgreSqlConnector("localhost", 5432, "postgres", "postgres", "postgres");

    connector.execute("INSERT INTO nauczyciele VALUES (DEFAULT, Jan, Kowalsky");
    ResultSet result = connector.execute("SELECT * FROM nauczyciele");

    assertThat(result.next()).isTrue();
    assertThat(result.getString(1)).isEqualTo("Jan");
    assertThat(result.getString(2)).isEqualTo("Kowalsky");
    assertThat(result.next()).isFalse();
  }
}
