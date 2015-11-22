package com.SocScore.framework;

import com.SocScore.framework.data.Match;
import com.SocScore.framework.data.Team;

import java.time.LocalDateTime;

public class BatchInput extends ScoreKeeper {

    public void createMatch(Team team1, Team team2, LocalDateTime startTime, LocalDateTime endTime) {
        setCurrentMatch(new Match(team1, team2, startTime, endTime));
        getMATCHES().add(getCurrentMatch());
    }
}
