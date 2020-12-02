package com.api.service;

import com.api.dto.ChampionshipTeamDto;
import com.api.entities.Championship;
import com.api.repository.ChampionshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ChampionshipService {
    @Autowired ChampionshipRepository championshipRepository;
    @Autowired TeamService teamService;

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
}