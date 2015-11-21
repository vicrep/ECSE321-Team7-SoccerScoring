package com.SocScore.framework.data;

import java.time.LocalDateTime;

public class Match {
    private static int count = 0;

    private final int MATCH_ID = count++;
    private final Team TEAM1;
    private final Team TEAM2;

    private int winnerTeamID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int team1Score = 0;
    private int team2Score = 0;
    private boolean isActive = false;

    //setters and getters
    public int getMATCH_ID() {
        return MATCH_ID;
    }

    public int getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(int team1Score) {
        this.team1Score = team1Score;
    }

    public int getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(int team2Score) {
        this.team2Score = team2Score;
    }

    //constructor
    public Match(Team TEAM1, Team TEAM2) {
        this.TEAM1 = TEAM1;
        this.TEAM2 = TEAM2;
    }

    //methods
    public void startMatch(LocalDateTime startTime) throws Exception {
        if (isActive) throw new Exception("Cannot start a match that has already started or ended");
        this.startTime = startTime;
        TEAM1.startMatch();
        TEAM2.startMatch();
        isActive = true;
    }

    public void endMatch(LocalDateTime endTime) throws Exception {
        if (isActive) throw new Exception("Cannot end a match that hasn't started");
        this.endTime = endTime;
        if(team1Score<team2Score) {
            winnerTeamID = TEAM2.getTEAM_ID();
        }
        else if(team2Score<team1Score) {
            winnerTeamID = TEAM1.getTEAM_ID();
        }
        else {
            winnerTeamID = -1;
        }
        TEAM1.endMatch(winnerTeamID, team1Score);
        TEAM2.endMatch(winnerTeamID, team2Score);
    }
    public void incrementTeamScore(int TEAM_ID) throws Exception {
        if(TEAM1.getTEAM_ID() == TEAM_ID) team1Score++;
        else if(TEAM2.getTEAM_ID() == TEAM_ID) team2Score++;
        else throw new Exception("Cannot add score for player not currently in com.SocScore.framework.Match");
    }
}
