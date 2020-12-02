package com.api.service;

import com.api.dto.HobbyDto;
import com.api.entities.Hobby;
import com.api.repository.HobbyRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HobbyService {
  @Autowired private HobbyRepository hobbyRepository;

  public Hobby save(Hobby hobby) {
    return this.hobbyRepository.save(hobby);
  }

  public List<Hobby> findAll() {
    return this.hobbyRepository.findAll();
  }

  public Hobby findById(Long id) {
    return this.hobbyRepository.findById(id).get();
  }

  public Hobby convertToEntity(HobbyDto hobbyDto) {
    return new Hobby(
        hobbyDto.getName(),
        hobbyDto.getImage1(),
        hobbyDto.getImage2(),
        hobbyDto.getDescription(),
        hobbyDto.getNrOfPlayers());
  }
}
