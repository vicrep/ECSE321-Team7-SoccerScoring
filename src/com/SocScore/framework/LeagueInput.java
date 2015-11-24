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
        for(Player player : team.getPlayers()) {
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

    public static void main(String[] args) {
        LeagueInput test = new LeagueInput();
        test.addTeamToLeague("France");
        test.addNewPlayerToTeam("Jacques", 0);
        test.addTeamToLeague("Italy");
        test.addNewPlayerToTeam("Gio", 1);
        LeagueAnalysis.saveLeagueToDisk();
        PlayerAnalysis.savePlayersToDisk();


        LeagueAnalysis.loadLeagueFromDisk();
        PlayerAnalysis.loadPlayersFromDisk();
//        PlayerAnalysis.findPlayer(0).setTeamID(1);
//        System.out.println(LeagueAnalysis.findTeam(1).getPlayers().get(0).getTeamID());

//        LeagueAnalysis.saveLeagueToDisk();
//        PlayerAnalysis.savePlayersToDisk();



    }
}
