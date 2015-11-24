package com.SocScore.framework;

import com.SocScore.framework.data.LeagueAnalysis;
import com.SocScore.framework.data.Match;
import com.SocScore.framework.data.Team;

import java.time.LocalDateTime;

public class BatchInput extends ScoreKeeper {


    public void createMatch(Team team1, Team team2, LocalDateTime startTime, LocalDateTime endTime) {
        currentMatch = new Match(team1, team2, startTime, endTime);
        MATCHES.add(currentMatch);
        hasUnsavedMatches = true;
    }

    public void saveMatch() {
        currentMatch.updateScore();
    }

    public void addAllMatchesToLeague() {
        int numberOfMatchesToSave = MATCHES.size();
        for(int i = 0; i < numberOfMatchesToSave; i++) {
            MATCHES.get(i).updateScore();
            transferMatchToLeague(MATCHES.get(i));
        }
    }

    public void loadMatchFromLeague(int ID) {
        currentMatch = LeagueAnalysis.findMatch(ID);
        MATCHES.add(currentMatch);
        LeagueAnalysis.removeMatch(currentMatch);
        hasUnsavedMatches = true;
    }
}
