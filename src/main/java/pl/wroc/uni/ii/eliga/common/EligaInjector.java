package pl.wroc.uni.ii.eliga.common;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class EligaInjector {
  public static Injector injector;

  public static Injector getInjector() {
    if (injector == null) {
      injector = Guice.createInjector(new InjectionConfiguration());
    }
    return injector;
  }

  public static <T> T getInstance(Class<T> clazz) {
    return getInjector().getInstance(clazz);
  }
}
