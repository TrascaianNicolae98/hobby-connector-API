package com.api.service;

import com.api.entities.Hobby;
import com.api.entities.TemporaryTeam;
import com.api.repository.TemporaryTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemporaryTeamService {
    @Autowired
    private TemporaryTeamRepository temporaryTeamRepository;
    public TemporaryTeam getByCapIdAndHobby(Long captainId, Hobby hobby){
        return this.temporaryTeamRepository.getTemporaryTeamByCaptainIdAndHobby(captainId,hobby);
    }
}
