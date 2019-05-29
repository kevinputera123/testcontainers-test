package com.test.testcontainerstest.dao;

import com.test.testcontainerstest.entity.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:home-dao-properties-test.properties")
public class HomeDaoPropertiesTest {
  private static final Logger log = LoggerFactory.getLogger(HomeDaoPropertiesTest.class);

  @Autowired
  private HomeDaoProperties homeDaoProperties;

  @Test
  public void testFindAll() {
    homeDaoProperties.save(new Country("Bolivia"));
    homeDaoProperties.save(new Country("Argentina"));
    homeDaoProperties.save(new Country("Brazil"));

    List<Country> queried = homeDaoProperties.findAll();
    List<Country> expected = new ArrayList<>();

    Assertions.assertNotNull(queried);

    expected.add(new Country("Argentina"));
    expected.add(new Country("Bolivia"));
    expected.add(new Country("Brazil"));

    queried.sort(Comparator.comparing(Country::getName));

    log.info("");
    log.info("testFindAll():");

    for (int c = 0; c < expected.size() && c < queried.size(); c++){
      String queriedName = queried.get(c).getName();
      String expectedName = expected.get(c).getName();
      log.info(String.format("Comparing: %s and %s", queriedName, expectedName));
      Assertions.assertEquals(expectedName, queriedName);
    }
  }

  @Test
  public void testFindByName() {
    List<Country> queried = homeDaoProperties.findByName("Bolivia");

    Assertions.assertNotNull(queried);

    log.info("");
    log.info("testFindByName():");
    log.info("Query name: Bolivia");

    for (Country country : queried) {
      log.info(String.format("Found name: %s", country.getName()));
      Assertions.assertEquals("Bolivia", country.getName());
    }
  }
}
