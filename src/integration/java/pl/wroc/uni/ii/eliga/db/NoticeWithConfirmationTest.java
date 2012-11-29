package pl.wroc.uni.ii.eliga.db;

import static org.fest.assertions.Assertions.assertThat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class NoticeWithConfirmationTest extends SqlBasicTest{

	@Before
	  public void cleanUpDatabase() throws SQLException {
	    connector.executeUpdate("TRUNCATE TABLE uczniowie CASCADE");
	    connector.executeUpdate("TRUNCATE TABLE nauczyciele CASCADE");
	    connector.executeUpdate("TRUNCATE TABLE uwagi CASCADE");
	  }

	  @Test
	  public void shouldCerateNoticeWithConfirmation() throws SQLException {
	    connector.executeUpdate("INSERT INTO uczniowie VALUES (1, 'Adam', 'Testowy', '95110411995')");
	    connector.executeUpdate("INSERT INTO nauczyciele VALUES (1, 'Nauczyciel', 'testowy')");
	    connector.executeUpdate("INSERT INTO uwagi VALUES (1, 'Naganne zachowanie', '12.12.2012','false' '1', '1')");
	    
	    ResultSet result = connector.execute("SELECT * FROM uwagi where uwagi.id = 1");
	    String data = result.getString(3);
	    int day = Integer.parseInt(data.substring(0, 3));
		int month = Integer.parseInt(data.substring(3, 5)); 
	    int year = Integer.parseInt(data.substring(5, 9));	
	    
	    Notice notice = new Notice(result.getInt(1), result.getString(2), new Date(year,month,day) , result.getString(4), result.getInt(5), result.getInt(6));
	    
	    assertThat(notice.getId()).isEqualTo(1);
	    assertThat(notice.getDesc()).isEqualTo("Naganne zachowanie");
	    assertThat(notice.getDate()).isEqualTo(data);
	    assertThat(notice.getConfirmation()).isEqualTo(false);
	    assertThat(notice.getStudentId()).isEqualTo(1);
	    assertThat(notice.getTeacherId()).isEqualTo(1);
	  }
	
	
}
