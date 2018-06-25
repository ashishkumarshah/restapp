package com.phodu.rest.application;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
//import io.swagger.jaxrs.listing.ApiListingResource;
//import io.swagger.jaxrs.listing.SwaggerSerializers;
//import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("/rest")
public class RestApp extends Application {
  /*
  public RestApp() {
          BeanConfig beanConfig = new BeanConfig();
          beanConfig.setVersion("1.0.2");
          beanConfig.setSchemes(new String[]{"http"});
          beanConfig.setHost("localhost:8080");
          beanConfig.setBasePath("/restapp");
          beanConfig.setResourcePackage("com.phodu.rest.api");
          beanConfig.setScan(true);
  }*/
  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> classes = new HashSet<Class<?>>();
    try {
      for (String resourceClassName : getResourceList()) {
        Class resourceClass = Class.forName(resourceClassName);
        classes.add(resourceClass);
      }
      /*s.add(io.swagger.jaxrs.listing.ApiListingResource.class);
      s.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
      */
    } catch (ClassNotFoundException classNotFoundException) {
    }
    return classes;
  }

  public Set<String> getResourceList() {
    Properties prop = new Properties();
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream input = null;
    Set<String> resourceClassNames = new HashSet<String>();

    try {

      input = classLoader.getResourceAsStream("RestAppRegistry.properties");
      prop.load(input);
      String resources = prop.getProperty("resources");
      String[] resourcesList = resources.split(",");
      for (String resource : resourcesList) {
        resourceClassNames.add(resource);
      }

    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return resourceClassNames;
  }
}
