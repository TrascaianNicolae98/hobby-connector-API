package com.api.service;

import com.api.dto.InvitePlayerDto;
import com.api.dto.TemporaryTeamDto;
import com.api.dto.TemporaryTeamResponse;
import com.api.dto.TemporaryTeamUserDto;
import com.api.entities.AppUser;
import com.api.entities.Hobby;
import com.api.entities.TemporaryTeam;
import com.api.entities.TemporaryTeamUser;
import com.api.repository.TemporaryTeamUserRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemporaryTeamUserService {
  @Autowired private TemporaryTeamUserRepository temporaryTeamUserRepository;
  @Autowired private TemporaryTeamService temporaryTeamService;
  @Autowired private HobbyService hobbyService;
  @Autowired private AppUserService userService;

  public void add(TemporaryTeamUser temporaryTeamUser) {
    this.temporaryTeamUserRepository.save(temporaryTeamUser);
  }

  public ArrayList<AppUser> getAllUsers(TemporaryTeam temporaryTeam) {
    ArrayList<TemporaryTeamUser> temporaryTeamUsers =
        this.temporaryTeamUserRepository.getAllByTemporaryTeam(temporaryTeam);
    ArrayList<AppUser> appUsers = new ArrayList<>();
    for (TemporaryTeamUser temporaryTeamUser : temporaryTeamUsers) {
      appUsers.add(temporaryTeamUser.getAppUser());
    }
    return appUsers;
  }

  public TemporaryTeamDto getTournametUsers(TemporaryTeamResponse temporaryTeamResponse) {
    Long captainId = temporaryTeamResponse.getCaptainId();
    Hobby hobby = this.hobbyService.findById(temporaryTeamResponse.getHobbyId());
    ArrayList<AppUser> amicalUsers =
        this.getAllUsers(this.temporaryTeamService.getTemporaryTeam(captainId, "amical", hobby));
    ArrayList<AppUser> championshipUsers =
        this.getAllUsers(
            this.temporaryTeamService.getTemporaryTeam(captainId, "championship", hobby));

    ArrayList<TemporaryTeamUserDto> amicalTemporaryTeamUserDtos = new ArrayList<>();
    ArrayList<TemporaryTeamUserDto> championshipTemporaryTeamUserDtos = new ArrayList<>();

    for (AppUser appUser : amicalUsers) {
      amicalTemporaryTeamUserDtos.add(this.convertToDto(appUser));
    }

    for (AppUser appUser : championshipUsers) {
      championshipTemporaryTeamUserDtos.add(this.convertToDto(appUser));
    }

    return new TemporaryTeamDto(amicalTemporaryTeamUserDtos, championshipTemporaryTeamUserDtos);
  }

  public TemporaryTeamUserDto convertToDto(AppUser appUser) {
    return new TemporaryTeamUserDto(
        appUser.getId(), appUser.getFullName(), appUser.getPhoneNo(), appUser.getEmail());
  }

  public String sendInvite(InvitePlayerDto invitePlayerDto) {
    TemporaryTeam temporaryTeam =
        this.temporaryTeamService.temporaryTeamRepository.getByCaptainIdAndTypeAndHobby(
            invitePlayerDto.getCaptainId(),
            invitePlayerDto.getTeamType(),
            this.hobbyService.findById(invitePlayerDto.getHobbyId()));
    if (temporaryTeam == null) {
      temporaryTeam =
          new TemporaryTeam(
              invitePlayerDto.getTeamType(),
              this.hobbyService.findById(invitePlayerDto.getHobbyId()),
              invitePlayerDto.getCaptainId());
      this.temporaryTeamService.addTemporaryTeam(temporaryTeam);
    }

    TemporaryTeamUser temporaryTeamUser =
        new TemporaryTeamUser(
            this.userService.getUserByEmail(invitePlayerDto.getPlayerEmail()), temporaryTeam, 1);
    this.temporaryTeamUserRepository.save(temporaryTeamUser);
    return "Ok";
  }
}
