package pl.wroc.uni.ii.eliga.test.util;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import static pl.wroc.uni.ii.eliga.common.EligaInjector.getInstance;

public class GuiceJUnitRunner extends BlockJUnit4ClassRunner {
  private Class<?> klass;

  public GuiceJUnitRunner(Class<?> klass) throws InitializationError {
    super(klass);
    this.klass = klass;
  }

  @Override
  protected Object createTest() throws Exception {
    return getInstance(klass);
  }
}
