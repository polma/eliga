package pl.wroc.uni.ii.eliga.common;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import pl.wroc.uni.ii.eliga.db.DatabaseConnector;
import pl.wroc.uni.ii.eliga.db.DatabaseService;
import pl.wroc.uni.ii.eliga.db.postgres.PostgreSqlConnector;
import pl.wroc.uni.ii.eliga.db.postgres.PostgreSqlService;

import static pl.wroc.uni.ii.eliga.common.EligaSettings.*;

public class InjectionConfiguration extends AbstractModule {
  private static Injector injector;

  public static Injector getInjector() {
    if(injector == null) {
      injector = Guice.createInjector(new InjectionConfiguration());
    }
    return injector;
  }

  @Override
  protected void configure() {
    bind(DatabaseService.class).to(PostgreSqlService.class);
    bind(DatabaseConnector.class).to(PostgreSqlConnector.class);

    bind(String.class).annotatedWith(Names.named(DB_HOST_PARAM)).toInstance("localhost");
    bind(String.class).annotatedWith(Names.named(DB_NAME_PARAM)).toInstance("postgres");
    bind(String.class).annotatedWith(Names.named(DB_USER_PARAM)).toInstance("postgres");
    bind(String.class).annotatedWith(Names.named(DB_PASS_PARAM)).toInstance("postgres");
    bind(Integer.class).annotatedWith(Names.named(DB_PORT_PARAM)).toInstance(5433);
  }
}
