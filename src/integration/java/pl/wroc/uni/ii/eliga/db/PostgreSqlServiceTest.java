package pl.wroc.uni.ii.eliga.db;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static org.apache.commons.lang3.time.DateUtils.parseDate;
import static org.fest.assertions.Assertions.assertThat;

public class PostgreSqlServiceTest extends SqlBasicTest {
    private DatabaseService db;

    @Before
    public void setUp() {
        db = new PostgreSqlService(connector);
    }

    @Test
    public void databaseServiceGetsValidInformationFromDatabase() throws SQLException, ParseException {
        connector.executeUpdate("INSERT INTO uczniowie VALUES (1, 'Adam', 'Testowy', '95110411995')");
        connector.executeUpdate("INSERT INTO nauczyciele VALUES (1, 'Nauczyciel', 'testowy')");
        connector.executeUpdate("INSERT INTO uwagi VALUES ('1', 'Naganne zachowanie', '12.12.2012', '1', '1')");

        List<Notice> notices = db.fetchNoticesNewerThan(parseDate("11.12.2012", "dd.MM.yyyy"));
        assertThat(notices).containsExactly(new Notice(1, "Naganne zachowanie", parseDate("12.12.2012", "dd.MM.yyyy"), 1, 1));

        Teacher teacher = db.getTeacherById(1);
        assertThat(teacher).isEqualTo(new Teacher(1, "Nauczyciel", "testowy"));

        Student student = db.getStudentFor(notices.get(0));
        assertThat(student).isEqualTo(new Student(1, "Adam", "Testowy", "95110411995"));
    }
}
