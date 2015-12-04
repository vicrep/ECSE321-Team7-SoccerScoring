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

    public void saveDataToDisk() {
        LeagueAnalysis.saveLeagueToDisk();
        PlayerAnalysis.savePlayersToDisk();
    }
// ******* For Testing Purposes *******
//    public static void main(String[] args) {
//        LeagueInput test = new LeagueInput();
//        test.addTeamToLeague("France");
//        test.addNewPlayerToTeam("Jacques", 0);
//        test.addTeamToLeague("Italy");
//        test.addNewPlayerToTeam("Gio", 1);
//        for(int i = 0; i < 10; i++) {
//            test.addNewPlayerToTeam("Player " + i, 0);
//            test.addNewPlayerToTeam("Player " + (i+10), 1);
//        }
//
//        BatchInput testBatch = new BatchInput();
//
//        testBatch.createMatch(LeagueAnalysis.findTeam(0), LeagueAnalysis.findTeam(1), new LocalDateTime(), new LocalDateTime().plusMinutes(80));
//        try {
//            testBatch.shoots(0, true, new LocalDateTime().plusMinutes(30));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            testBatch.saveMatch();
//            testBatch.addAllMatchesToLeague();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        test.saveDataToDisk();
//        test.loadDataFromDisk();
//        test.saveDataToDisk();
//    }
}
