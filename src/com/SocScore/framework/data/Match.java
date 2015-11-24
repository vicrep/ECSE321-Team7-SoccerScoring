package com.SocScore.framework.data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

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
    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.TEAM1_ID = team1.getTEAM_ID();
        this.TEAM2_ID = team2.getTEAM_ID();
    }

    public Match(Team team1, Team team2, LocalDateTime startTime, LocalDateTime endTime) {
        this(team1, team2);
        this.startTime = startTime;
        this.endTime = endTime;
        hasEnded = true;
    }

    void resetTeams() {
        team1 = LeagueAnalysis.findTeam(TEAM1_ID);
        team2 = LeagueAnalysis.findTeam(TEAM2_ID);
        hasEnded = true;
        isActive = false;
    }

    //methods
    public void startMatch(LocalDateTime startTime) throws RuntimeException {
        if (isActive || hasEnded) throw new RuntimeException("Cannot start a match that has already started or ended");
        this.startTime = startTime;
        team1.startMatch();
        team2.startMatch();
        isActive = true;
    }

    public void endMatch(LocalDateTime endTime) throws RuntimeException {
        if (!isActive) throw new RuntimeException("Cannot end a match that hasn't started");
        this.endTime = endTime;
        updateScore();
        hasEnded = true;
    }

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

    public void incrementTeamScore(int TEAM_ID) throws RuntimeException {
        if(team1.getTEAM_ID() == TEAM_ID) team1Score++;
        else if(team2.getTEAM_ID() == TEAM_ID) team2Score++;
        else throw new RuntimeException("Cannot add score for player not currently in com.SocScore.framework.Match");
    }

    public boolean playerIsInMatch(Player player) {
        return (team1.getPlayers().contains(player) || team2.getPlayers().contains(player));
    }

    public long getElapsedTime() {
        if(isActive) return ChronoUnit.MINUTES.between(startTime, LocalDateTime.now());
        else return getTotalTime();
    }

    public long getTotalTime() {
        if(isActive) return getElapsedTime();
        else return ChronoUnit.MINUTES.between(startTime, endTime);
    }

    //comparators for sorting
    public static Comparator<Match> sortByID = (m1, m2) -> m1.getMATCH_ID() - m2.getMATCH_ID();

    public static Comparator<Match> sortByTime = (m1, m2) -> m1.getStartTime().compareTo(m2.getStartTime());

    //setters and getters
    public int getMATCH_ID() {
        return MATCH_ID;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public int getTeam1Score() {
        return team1Score;
    }

    public int getTeam2Score() {
        return team2Score;
    }

    public int getTEAM1_ID() {
        return TEAM1_ID;
    }

    public int getTEAM2_ID() {
        return TEAM2_ID;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
