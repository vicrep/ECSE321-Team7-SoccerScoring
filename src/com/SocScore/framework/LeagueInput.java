package com.SocScore.framework;

import com.SocScore.framework.data.*;

public class LeagueInput extends AnalysisViewer {

    public void removePlayerFromLeague(int playerID) {
        Player player = PlayerAnalysis.findPlayer(playerID);
        LeagueAnalysis.findTeam(player.getTeamID()).removePlayer(player);
        PlayerAnalysis.removePlayer(player);
    }

    public void addNewPlayerToTeam(String name, int teamID) {
        Player newPlayer = new Player(name, teamID);
        LeagueAnalysis.findTeam(teamID).addPlayer(newPlayer);
        PlayerAnalysis.addPlayer(newPlayer);
    }

    public void addTeamToLeague(String name) {
        LeagueAnalysis.addTeam(new Team(name));
    }

    public void removeTeamFromLeague(int teamID) {
        Team team = LeagueAnalysis.findTeam(teamID);
        for(Player player : team.getPLAYERS()) {
            PlayerAnalysis.removePlayer(player);
        }
        LeagueAnalysis.removeTeam(team);
    }

    public void transferPlayer(int playerID, int oldTeamID, int newTeamID) {
        Player player = PlayerAnalysis.findPlayer(playerID);
        LeagueAnalysis.findTeam(oldTeamID).removePlayer(player);
        LeagueAnalysis.findTeam(newTeamID).addPlayer(player);
    }

    public void removeMatchFromLeague(int matchID) {
        Match match = LeagueAnalysis.findMatch(matchID);
        LeagueAnalysis.removeMatch(match);
    }
}
