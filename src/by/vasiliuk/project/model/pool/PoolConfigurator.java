package by.vasiliuk.project.model.pool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class PoolConfigurator {
    protected static final String DB_HOST = "db.host";
    protected static final String DB_LOGIN = "db.login";
    protected static final String DB_PASSWORD = "db.password";
    protected static final String CONNECTION_TIMEOUT = "wait-connection-timeout";
    protected static final String DB_DRIVER = "db.driver-name";

  static final  PoolConfigurator configurator = new PoolConfigurator();
  private static final String CONFIG_PROPERTIES = "resources//config.properties";
  private  Properties properties = new Properties();
  private PoolConfigurator() {
   InputStream input;
   try {
    input = this.getClass().getClassLoader().getResourceAsStream(CONFIG_PROPERTIES);
    properties.load(input);
   } catch (IOException e){
    throw new RuntimeException("Properties file not found", e);
   }
  }
 static PoolConfigurator getConfigurator() {
   return configurator;
}
  Properties getProperties() {
  return properties;
 }
}
