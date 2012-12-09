package pl.wroc.uni.ii.eliga.db;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import static pl.wroc.uni.ii.eliga.common.EligaInjector.getInstance;

public class SchemaGenerator {

  public void createSchema() {
    Configuration conf = getInstance(Configuration.class);

    SchemaExport schemaExport = new SchemaExport(conf);
    schemaExport.setOutputFile("create_tables.sql");
    schemaExport.setDelimiter(";");
    schemaExport.create(true, true);
  }
}
