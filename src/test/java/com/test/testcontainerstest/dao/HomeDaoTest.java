package com.test.testcontainerstest.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.testcontainerstest.entity.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class HomeDaoTest {
  private static final Logger log = LoggerFactory.getLogger(HomeDaoTest.class);

  @Autowired
  public HomeDao homeDao;

  @Test
  public void testFindAll() {
    homeDao.save(new Country("Bolivia"));
    homeDao.save(new Country("Argentina"));
    homeDao.save(new Country("Brazil"));

    List<Country> queried = homeDao.findAll();
    List<Country> expected = new ArrayList<>();

    expected.add(new Country("Bolivia"));
    expected.add(new Country("Argentina"));
    expected.add(new Country("Brazil"));

    expected.sort(new Comparator<Country>() {
      @Override
      public int compare(Country o1, Country o2) {
        return o1.getName().compareTo(o2.getName());
      }
    });

    log.info("");
    log.info("testFindAll():");
    for (int c = 0; c < expected.size() && c < queried.size(); c++){
      String queriedName = queried.get(c).getName();
      String expectedName = expected.get(c).getName();
      log.info(String.format("Comparing: %s and %s", queriedName, expectedName));
      assertEquals(expectedName, queriedName);
    }
  }

  @Test
  public void testFindByName() {
    List<Country> queried = homeDao.findByName("Bolivia");

    log.info("");
    log.info("testFindByName():");
    log.info("Query name: Bolivia");
    for (Country country : queried) {
      log.info(String.format("Found name: %s", country.getName()));
      assertEquals("Bolivia", country.getName());
    }
  }
}
