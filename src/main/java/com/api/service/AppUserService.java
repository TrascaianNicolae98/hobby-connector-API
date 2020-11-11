package com.api.service;

import com.api.entities.AppUser;
import com.api.repository.AppUserRepository;
import com.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
  @Autowired private AppUserRepository appUserRepository;
  @Autowired private JwtUtil jwtUtil;

  public void add(AppUser appUser) {
    this.appUserRepository.save(appUser);
  }

  public Boolean userExist(String email) {
    AppUser auxAppUser = this.getUserByEmail(email);
    return auxAppUser != null;
  }

  public AppUser getUserByEmail(String email) {
    return this.appUserRepository.getAppUserByEmail(email);
  }

  public JwtUtil getJwtUtil() {
    return this.jwtUtil;
  }
}
