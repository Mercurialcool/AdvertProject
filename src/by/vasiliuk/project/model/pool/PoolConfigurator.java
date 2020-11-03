package by.vasiliuk.project.model.pool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class PoolConfigurator {
  static final  PoolConfigurator configurator = new PoolConfigurator();
  private static final String CONFIG_PROPERTIES = "resources//config.properties";
  private  Properties properties = new Properties();
  // todo logger
  private PoolConfigurator() {
   InputStream input;
   try {
    input = this.getClass().getClassLoader().getResourceAsStream(CONFIG_PROPERTIES);
    properties.load(input);
   } catch (IOException e){
    //todo fatal log
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
