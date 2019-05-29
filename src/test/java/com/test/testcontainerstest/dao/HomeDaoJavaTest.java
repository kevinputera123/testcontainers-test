package com.test.testcontainerstest.dao;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.testcontainerstest.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HomeDaoJavaTest extends DaoJavaTest {
  private static final Logger log = LoggerFactory.getLogger(HomeDaoJavaTest.class);

  @Autowired
  private HomeDaoJava homeDaoJava;

  @Test
  public void testFindAll() {
    homeDaoJava.save(new Country("Bolivia"));
    homeDaoJava.save(new Country("Argentina"));
    homeDaoJava.save(new Country("Brazil"));

    List<Country> queried = homeDaoJava.findAll();
    List<Country> expected = new ArrayList<>();

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
    List<Country> queried = homeDaoJava.findByName("Bolivia");

    log.info("");
    log.info("testFindByName():");
    log.info("Query name: Bolivia");
    for (Country country : queried) {
      log.info(String.format("Found name: %s", country.getName()));
      Assertions.assertEquals("Bolivia", country.getName());
    }
  }
}
