package com.SocScore.framework.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Team {
    private static int count = 0;

    private final int TEAM_ID = count++;
    private final List<Player> PLAYERS = new ArrayList<>();

    private int numOfMatchesPlayed = 0;
    private int numOfVictories = 0;
    private int numOfLosses = 0;
    private int numOfDraws = 0;
    private int teamScore = 0;
    private int totalGoalsScored = 0;
    private String name;

    //Constructor
    public Team(String name) {
        this.name = name;
    }

    //Methods
    public void addPlayer(Player player) {
        PLAYERS.add(player);
    }

    public void removePlayer(Player player) {
        PLAYERS.remove(player);
    }

    public void startMatch() throws Exception {
        if(PLAYERS.size() < 11) throw new Exception("Teams must have at least 11 players to start a match");
        PLAYERS.forEach(Player::startMatch);
    }

    public void endMatch(int winnerID, int goals) {
        if(winnerID == TEAM_ID) numOfVictories++;
        else if(winnerID == -1) numOfDraws++;
        else numOfLosses++;

        PLAYERS.forEach(Player::endMatch);
        updateTeamScore();
        totalGoalsScored += goals;
    }

    private void updateTeamScore() {
        teamScore = (3*numOfVictories) + numOfDraws;
        numOfMatchesPlayed = numOfVictories + numOfDraws + numOfLosses;
    }

    //comparators (for ranking)
    public static Comparator<Team> rankByID = (t1, t2) -> t1.getTEAM_ID() - t2.getTEAM_ID();

    public static Comparator<Team> rankByScore = (t1, t2) -> t2.getTeamScore() - t1.getTeamScore();

    public static Comparator<Team> rankByTotalGoals = (t1, t2) -> t2.getTotalGoalsScored() - t1.getTotalGoalsScored();



    //Setters and Getters
    public int getTEAM_ID() {
        return TEAM_ID;
    }

    public List<Player> getPLAYERS() {
        return PLAYERS;
    }

    public int getNumOfMatchesPlayed() {
        return numOfMatchesPlayed;
    }

    public int getNumOfVictories() {
        return numOfVictories;
    }

    public void setNumOfVictories(int numOfVictories) {
        this.numOfVictories = numOfVictories;
        updateTeamScore();
    }

    public int getNumOfLosses() {
        return numOfLosses;
    }

    public void setNumOfLosses(int numOfLosses) {
        this.numOfLosses = numOfLosses;
        updateTeamScore();
    }

    public int getNumOfDraws() {
        return numOfDraws;
    }

    public void setNumOfDraws(int numOfDraws) {
        this.numOfDraws = numOfDraws;
        updateTeamScore();
    }

    public int getTeamScore() {
        return teamScore;
    }

    public int getTotalGoalsScored() {
        return totalGoalsScored;
    }

    public String getName() {
        return name;
    }

}
