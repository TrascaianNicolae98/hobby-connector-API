package com.api.repository;

import com.api.entities.Hobby;
import com.api.entities.Team;
import com.api.entities.TemporaryTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemporaryTeamRepository extends JpaRepository<TemporaryTeam, Long> {
    public TemporaryTeam getTemporaryTeamByCaptainIdAndHobby(Long captainId, Hobby hobby);
}
