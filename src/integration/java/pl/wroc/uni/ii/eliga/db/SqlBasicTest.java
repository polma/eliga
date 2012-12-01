package pl.wroc.uni.ii.eliga.db;

import org.junit.Before;

import java.sql.SQLException;
import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;

public class SqlBasicTest {
	protected PostgreSqlConnector connector = new PostgreSqlConnector("localhost", 5433, "postgres", "postgres", "postgres");

    protected List<String> allTables = asList("nauczyciele", "uczniowie", "uwagi");

    @Before
    public void cleanUpDatabase() throws SQLException {
        for(String table : allTables) {
            connector.executeUpdate(format("TRUNCATE TABLE %s CASCADE", table));
        }
    }
}
