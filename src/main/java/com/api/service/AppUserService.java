package com.api.service;

import com.api.entities.AppUser;
import com.api.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
  @Autowired private AppUserRepository appUserRepository;

  public void add(AppUser appUser) {
    this.appUserRepository.save(appUser);
  }
}
