package com.test.testcontainerstest.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest
@ContextConfiguration(initializers = DaoJavaTest.Initializer.class)
public class DaoJavaTest {
  private static MySQLContainer mysql = new MySQLContainer("mysql:5.7");

  @AfterAll
  public static void stopContainer() {
    mysql.stop();
  }

  @BeforeAll
  public static void startContainer() {
    mysql.start();
  }

  static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      String jdbcUrl = mysql.getJdbcUrl();
      String username = mysql.getUsername();
      String password = mysql.getPassword();

      TestPropertyValues.of(
          "spring.datasource.url=" + jdbcUrl,
          "spring.datasource.username=" + username,
          "spring.datasource.password=" + password,
          "spring.jpa.hibernate.ddl-auto=create"
      ).applyTo(configurableApplicationContext);
    }
  }
}
