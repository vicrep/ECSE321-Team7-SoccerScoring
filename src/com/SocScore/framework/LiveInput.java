package com.SocScore.framework;

import com.SocScore.framework.data.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LiveInput extends ScoreKeeper {
    private List<Match> MATCHES = new ArrayList<>();
    private Match currentMatch;

    public void createMatch(Team team1, Team team2) {
        currentMatch = new Match(team1, team2);
        MATCHES.add(currentMatch);
    }

    public void startMatch() throws Exception {
        currentMatch.startMatch(LocalDateTime.now());
    }

    public void endMatch() throws Exception {
        currentMatch.endMatch(LocalDateTime.now());
        MATCHES.remove(currentMatch);
        LeagueAnalysis.addMatch(currentMatch);
        currentMatch = null;
    }

    public void selectMatch(int i) {
        currentMatch = MATCHES.get(i);
    }

    public void shoots(int playerID, boolean scored) throws Exception {
        Player player = PlayerAnalysis.findPlayer(playerID);
        if(scored) {
            currentMatch.incrementTeamScore(player.getTeamID());
        }
        player.shoots(LocalDateTime.now(), scored, currentMatch.getMATCH_ID());
    }

}
