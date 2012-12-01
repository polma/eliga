package pl.wroc.uni.ii.eliga.db;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.fest.assertions.Assertions.assertThat;

public class PostgreSqlConnectorTest extends SqlBasicTest {
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
