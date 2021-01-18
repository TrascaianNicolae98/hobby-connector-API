package com.api.repository;

import com.api.entities.Championship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChampionshipRepository extends JpaRepository<Championship, Long> {
  @Query("select c from Championship c  where c.name=?1")
  public Championship findChampionshipByName(String name);
}
