package com.SocScore.framework;

import com.SocScore.framework.data.LeagueAnalysis;
import com.SocScore.framework.data.Player;
import com.SocScore.framework.data.PlayerAnalysis;
import com.SocScore.framework.data.Team;

public class ScoreKeeper extends AnalysisViewer {

    void removePlayerFromLeague(int playerID) {
        Player player = PlayerAnalysis.findPlayer(playerID);
        LeagueAnalysis.findTeam(player.getTeamID()).removePlayer(player);
        PlayerAnalysis.removePlayer(player);
    }

    void addNewPlayerToTeam(String name, int teamID) {
        Player newPlayer = new Player(name, teamID);
        LeagueAnalysis.findTeam(teamID).addPlayer(newPlayer);
        PlayerAnalysis.addPlayer(newPlayer);
    }

    void addTeamToLeague(String name) {
        LeagueAnalysis.addTeam(new Team(name));
    }

    void removeTeamFromLeague(int teamID) {
        Team team = LeagueAnalysis.findTeam(teamID);
        for(Player player : team.getPLAYERS()) {
            PlayerAnalysis.removePlayer(player);
        }
        LeagueAnalysis.removeTeam(team);
    }

    void transferPlayer(int playerID, int oldTeamID, int newTeamID) {
        Player player = PlayerAnalysis.findPlayer(playerID);
        LeagueAnalysis.findTeam(oldTeamID).removePlayer(player);
        LeagueAnalysis.findTeam(newTeamID).addPlayer(player);
    }


}
