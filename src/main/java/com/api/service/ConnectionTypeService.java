package com.api.service;

import com.api.entities.ConnectionType;
import com.api.repository.ConnectionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionTypeService {
  @Autowired private ConnectionTypeRepository connectionTypeRepository;

  public void add(ConnectionType connectionType) {
    this.connectionTypeRepository.save(connectionType);
  }
}
