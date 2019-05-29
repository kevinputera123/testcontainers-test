package com.test.testcontainerstest.dao;

import com.test.testcontainerstest.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeDaoJava extends JpaRepository<Country, Long> {
  List<Country> findByName(String name);
}
