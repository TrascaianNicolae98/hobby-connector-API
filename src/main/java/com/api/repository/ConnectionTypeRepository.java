package com.api.repository;

import com.api.entities.ConnectionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionTypeRepository extends JpaRepository<ConnectionType, Long> {
  ConnectionType getConnectionTypeByName(String name);
}
