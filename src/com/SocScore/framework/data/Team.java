package com.SocScore.framework.data;

import org.joda.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Object which stores all information related to a single team.
 */
public class Team {
    private static int count = 0;

    private final int TEAM_ID = count++;

    private List<Player> players = new ArrayList<>();

    private int numOfMatchesPlayed = 0;
    private int numOfVictories = 0;
    private int numOfLosses = 0;
    private int numOfDraws = 0;
    private int teamScore = 0;
    private int totalGoalsScored = 0;
    private String name;



    //Constructor

    /**
     * Initializes a new team with given name.
     * @param name Name to be given to a team, doesn't have to be unique
     */
    public Team(String name) {
        this.name = name;
    }

    /**
     * Resets the list of {@link Player} to a new ArrayList, used when loading data from disk to prevent duplicates
     * @see PlayerAnalysis#loadPlayersFromDisk()
     */
    public void resetPlayers() {
        players = new ArrayList<>();
    }

    //Methods

    /**
     * Allows to add a new player to the team.
     * @param player Player object to be added to the team.
     */
    public void addPlayer(Player player) {
        players.add(player);
        player.setTeamID(TEAM_ID);
    }

    /**
     * Allows to remove a player from the team.
     * @param player Player object to be removed.
     */
    public void removePlayer(Player player) {
        players.remove(player);
    }

    /**
     * Starts a new match, sets all players to their initial player.
     * @throws RuntimeException Throws an exception if there aren't at least 11 players in the team
     */
    public void startMatch() throws RuntimeException {
        if(players.size() < 11) throw new RuntimeException("Teams must have at least 11 players to start a match");
        for(Player player : players) {
            player.startMatch();
        }
    }

    /**
     * Ends a match, and updates attributes for the team and its players.
     * @see #updateTeamScore()
     * @see Match#endMatch(LocalDateTime)
     * @param winnerID the team ID of the winning team.
     * @param goals the number of goals scored by the team.
     */
    public void endMatch(int winnerID, int goals) {
        if(winnerID == TEAM_ID) numOfVictories++;
        else if(winnerID == -1) numOfDraws++;
        else numOfLosses++;

        for(Player player : players) {
            player.endMatch();
        }
        updateTeamScore();
        totalGoalsScored += goals;
    }

    /**
     * Calculates the score of the team, based on its number of victories, draws, and losses
     */
    private void updateTeamScore() {
        teamScore = (3*numOfVictories) + numOfDraws;
        numOfMatchesPlayed = numOfVictories + numOfDraws + numOfLosses;
    }

    //comparators
    public static Comparator<Team> rankByID = new Comparator<Team>() {
        @Override
        public int compare(Team t1, Team t2) {
            return t1.getTEAM_ID() - t2.getTEAM_ID();
        }
    };

    public static Comparator<Team> rankByScore = new Comparator<Team>() {
        @Override
        public int compare(Team t1, Team t2) {
            return t2.getTeamScore() - t1.getTeamScore();
        }
    };

    public static Comparator<Team> rankByTotalGoals = new Comparator<Team>() {
        @Override
        public int compare(Team t1, Team t2) {
            return t2.getTotalGoalsScored() - t1.getTotalGoalsScored();
        }
    };



    //Setters and Getters
    public int getTEAM_ID() {
        return TEAM_ID;
    }

    public List<Player> getPlayers() {
        return players;
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
