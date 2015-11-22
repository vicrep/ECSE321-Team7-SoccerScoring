package com.SocScore.framework;

import com.SocScore.framework.data.*;

import java.time.LocalDateTime;

public class LiveInput extends ScoreKeeper {

    public void createMatch(Team team1, Team team2) {
        setCurrentMatch(new Match(team1, team2));
        getMATCHES().add(getCurrentMatch());
    }

    public void startMatch() throws Exception {
        getCurrentMatch().startMatch(LocalDateTime.now());
    }

    public void endMatch() throws Exception {
        getCurrentMatch().endMatch(LocalDateTime.now());
        transferMatchToLeague(getCurrentMatch());
    }

    public void shoots(int playerID, boolean scored) throws Exception {
        shoots(playerID, scored, LocalDateTime.now());
    }

    public void addInfraction(int playerID, InfractionType type) {
        addInfraction(playerID, type, LocalDateTime.now());
    }


}
