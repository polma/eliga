package pl.wroc.uni.ii.eliga.db;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.fest.assertions.Assertions.assertThat;
import static pl.wroc.uni.ii.eliga.db.TestObjects.teacher;

public class DatabaseConnectorTest extends SqlBasicTest {
  @Test
  public void interactsWithDatabase() throws SQLException {
    insertTeacher();
    ResultSet result = connector.execute("SELECT * FROM nauczyciele");

    assertThat(result.next()).isTrue();
    assertThat(result.getInt(1)).isEqualTo(teacher().getId());
    assertThat(result.getString(2)).isEqualTo(teacher().getName());
    assertThat(result.getString(3)).isEqualTo(teacher().getSurname());
    assertThat(result.next()).isFalse();
  }
}
