package com.api.service;

import com.api.dto.ChampionshipTeamDto;
import com.api.dto.TeamDto;
import com.api.dto.TeamSentChamp;
import com.api.entities.Championship;
import com.api.entities.Game;
import com.api.entities.Team;
import com.api.repository.ChampionshipRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChampionshipService {
  @Autowired ChampionshipRepository championshipRepository;
  @Autowired TeamService teamService;
  @Autowired HobbyService hobbyService;
  @Autowired AppUserService userService;

  public Championship save(ChampionshipTeamDto championshipTeamDto) {
    Championship championship = this.findById(championshipTeamDto.getChampionshipId());
    championship.addTeam(teamService.findById(championshipTeamDto.getTeamId()));
    championshipRepository.save(championship);
    return championship;
  }

  public Championship delete(Long id) {
    Championship championship = this.championshipRepository.findById(id).get();
    this.championshipRepository.deleteById(id);
    return championship;
  }

  public List<Championship> findAll() {
    return this.championshipRepository.findAll();
  }

  public Championship findById(Long id) {
    return this.championshipRepository.findById(id).get();
  }

  public String saveTeamChampionship(TeamSentChamp teamSentChamp) {
    Game game = new Game();
    Team team =
        new Team(
            teamSentChamp.getType(),
            teamSentChamp.getName(),
            game,
            this.hobbyService.findById(teamSentChamp.getHobbyId()));

    for (Long id : teamSentChamp.getPlayersIds()) {
      team.addUser(this.userService.findById(id));
    }
    this.teamService.save(team);
    for (Championship champ : this.championshipRepository.findAll()) {
      if (champ
          .getName()
          .equals(this.hobbyService.findById(teamSentChamp.getHobbyId()).getName())) {
        Championship championship;
        championship = this.championshipRepository.findById(champ.getId()).get();
        championship.addTeam(team);
        this.championshipRepository.save(championship);
        break;
      }
    }
    return "OK";
  }

  public ArrayList<TeamDto> getChampTeams(Long champId) {
    ArrayList<Team> teams =
        (ArrayList<Team>) this.championshipRepository.findById(champId).get().getTeamList();
    ArrayList<TeamDto> teamDtos = new ArrayList<>();
    for (Team team : teams) {
      teamDtos.add(
          new TeamDto(team.getType(), team.getName(), new Long(0), team.getHobby().getId()));
    }
    return teamDtos;
  }
}
