package com.SocScore.framework.data;

import org.joda.time.LocalDateTime;
import org.joda.time.Minutes;

import java.util.Comparator;

/**
 * Object representing an instance of a match, and all its relevant information.
 * @see LeagueAnalysis
 * @see com.SocScore.framework.scorekeeper.LiveInput
 * @see com.SocScore.framework.scorekeeper.BatchInput
 */
public class Match {
    private static int count = 0;

    private final int MATCH_ID = count++;
    private Team team1;
    private Team team2;
    private final int TEAM1_ID;
    private final int TEAM2_ID;

    private int winnerTeamID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int team1Score = 0;
    private int team2Score = 0;
    private boolean isActive = false;
    private boolean hasEnded = false;

    //constructor

    /**
     * Initializes a new instance of match, and adds the inputted teams to the match.
     * This constructor assumes the match has yet to happen.
     * @see com.SocScore.framework.scorekeeper.LiveInput
     * @param team1 First team to be added to the new match.
     * @param team2 Second team to be added to the new match.
     */
    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.TEAM1_ID = team1.getTEAM_ID();
        this.TEAM2_ID = team2.getTEAM_ID();
    }

    /**
     * Initializes a new instance of a match, and adds the teams to the match, as well as the start and end time.
     * This constructor assumes a match has already happened, and therefore sets the match's status as ended.
     * @param team1 First team to be added to the new match.
     * @param team2 Second team to be added to the new match.
     * @param startTime Time at which the match began.
     * @param endTime Time at which the match ended.
     */
    public Match(Team team1, Team team2, LocalDateTime startTime, LocalDateTime endTime) {
        this(team1, team2);
        this.startTime = startTime;
        this.endTime = endTime;
        hasEnded = true;
    }

    /**
     * Used by {@link LeagueAnalysis#loadLeagueFromDisk()} to perform re-linking teams and matches after a data load.
     */
    void resetTeams() {
        team1 = LeagueAnalysis.findTeam(TEAM1_ID);
        team2 = LeagueAnalysis.findTeam(TEAM2_ID);
        hasEnded = true;
        isActive = false;
    }

    //methods

    /**
     * Starts a new match, sets the match to active, stores the start time, and calls {@link Team#startMatch()} on both teams.
     * @param startTime The time at which the match started.
     * @throws RuntimeException Throws an exception if the match has already started, or has already ended.
     */
    public void startMatch(LocalDateTime startTime) throws RuntimeException {
        if (isActive || hasEnded) throw new RuntimeException("Cannot start a match that has already started or ended");
        this.startTime = startTime;
        team1.startMatch();
        team2.startMatch();
        isActive = true;
    }

    /**
     * Ends a currently happening match, stores the end time, and updates the score for both teams.
     * @see #updateScore()
     * @param endTime Time at which the match ended.
     * @throws RuntimeException Throws an exception if a match that hasn't started tries to be ended.
     */
    public void endMatch(LocalDateTime endTime) throws RuntimeException {
        if (!isActive) throw new RuntimeException("Cannot end a match that hasn't started");
        this.endTime = endTime;
        updateScore();
        hasEnded = true;
    }

    /**
     * Determines which team has won the game, stores it in a field, and sends this information over to both teams so they can update their respective fields.
     * @see Team#endMatch(int, int)
     */
    public void updateScore() {
        if(team1Score<team2Score) {
            winnerTeamID = team2.getTEAM_ID();
        }
        else if(team2Score<team1Score) {
            winnerTeamID = team1.getTEAM_ID();
        }
        else {
            winnerTeamID = -1;
        }
        team1.endMatch(winnerTeamID, team1Score);
        team2.endMatch(winnerTeamID, team2Score);
    }

    /**
     * Increments the score of the appropriate {@link Team} by 1.
     * @param TEAM_ID ID of the team who's score to increment.
     * @throws RuntimeException Throws an exception if it is attempted to increment the score of a team not in the match.
     */
    public void incrementTeamScore(int TEAM_ID) throws RuntimeException {
        if(team1.getTEAM_ID() == TEAM_ID) team1Score++;
        else if(team2.getTEAM_ID() == TEAM_ID) team2Score++;
        else throw new RuntimeException("Cannot add score for team not in the match");
    }

    /**
     * Determines whether a player is in the current match.
     * @param player Player object to check for presence in the current match.
     * @return Returns true if the player is in the match, false otherwise.
     */
    public boolean playerIsInMatch(Player player) {
        return (player.getTeamID() == TEAM1_ID || player.getTeamID() == TEAM2_ID);
    }

    /**
     * @return Returns the elapsed time in minutes of a match currently happening.
     */
    public long getElapsedTime() {
        if(isActive) return Minutes.minutesBetween(startTime, new LocalDateTime()).getMinutes();
        else return getTotalTime();
    }

    /**
     * @return Returns the total time of a match which has already ended.
     */
    public long getTotalTime() {
        if(isActive) return getElapsedTime();
        else return Minutes.minutesBetween(startTime, new LocalDateTime()).getMinutes();
    }

    //comparators for sorting
    public static Comparator<Match> sortByID = new Comparator<Match>() {
        @Override
        public int compare(Match m1, Match m2) {
            return m1.getMATCH_ID() - m2.getMATCH_ID();
        }
    };

    public static Comparator<Match> sortByTime = new Comparator<Match>() {
        @Override
        public int compare(Match m1, Match m2) {
            return m1.getStartTime().compareTo(m2.getStartTime());
        }
    };

    //setters and getters

    /**
     * @return Returns the unique ID of the match.
     */
    public int getMATCH_ID() {
        return MATCH_ID;
    }

    /**
     * @return Returns the first team of the match (by order added, not by score).
     */
    public Team getTeam1() {
        return team1;
    }

    /**
     * @return Returns the second team of the match (by order added, not by score).
     */
    public Team getTeam2() {
        return team2;
    }

    /**
     * @return Returns score of the first team of the match.
     */
    public int getTeam1Score() {
        return team1Score;
    }

    /**
     * @return Returns the score of the second team of the match.
     */
    public int getTeam2Score() {
        return team2Score;
    }

    /**
     * @return Returns the ID of the first team of the match.
     */
    public int getTEAM1_ID() {
        return TEAM1_ID;
    }

    /**
     * @return Returns the ID of the second team of the match.
     */
    public int getTEAM2_ID() {
        return TEAM2_ID;
    }

    /**
     * @return Returns the start time of the match.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * @return Returns the end time of the match.
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }
}
