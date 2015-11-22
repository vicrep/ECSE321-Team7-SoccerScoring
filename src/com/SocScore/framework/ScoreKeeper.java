package com.SocScore.framework;

import com.SocScore.framework.data.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class ScoreKeeper extends AnalysisViewer {
    private final List<Match> MATCHES = new ArrayList<>();
    private Match currentMatch;

    public void selectMatch(int i) {
        currentMatch = MATCHES.get(i);
    }

    public void shoots(int playerID, boolean scored, LocalDateTime time) throws Exception {
        Player player = PlayerAnalysis.findPlayer(playerID);
        if(currentMatch.playerIsInMatch(player)) {
            if(scored) {
                currentMatch.incrementTeamScore(player.getTeamID());
            }
            player.shoots(time, scored, currentMatch.getMATCH_ID());
        }
        else throw new RuntimeException("Cannot add a goal from a player who isn't currently in the match");
    }

    public void addInfraction(int playerID, InfractionType type, LocalDateTime time) {
        Player player = PlayerAnalysis.findPlayer(playerID);
        if(currentMatch.playerIsInMatch(player)) {
            player.commitsInfraction(type, time, currentMatch.getMATCH_ID());
        }
        else throw new RuntimeException("Cannot add an infraction to a player who isn't currently in the match");
    }

    public void transferMatchToLeague(Match match) {
        MATCHES.remove(match);
        LeagueAnalysis.addMatch(match);
    }

    public List<Match> getMATCHES() {
        return MATCHES;
    }

    public void setCurrentMatch(Match match) {
        this.currentMatch = match;
    }

    public Match getCurrentMatch() {
        return currentMatch;
    }
}
