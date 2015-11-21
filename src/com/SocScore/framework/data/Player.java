package com.SocScore.framework.data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Player {
    private static int count = 0;

    private final int PLAYER_ID = count++;
    private final String PLAYER_NAME;

    private final List<ShotOnGoal> SHOTS_ON_GOAL = new ArrayList<>();
    private final List<Infraction> INFRACTIONS = new ArrayList<>();

    private int teamID;

    private int numYellowCards = 0;
    private int numRedCards = 0;
    private int numPenaltyKicks = 0;
    private int numGoalsScored = 0;

    private int currentYellow;
    private int currentRed;
    private int currentPenalty;
    private int currentGoals;

    //setters and getters
    public int getPLAYER_ID() {
        return PLAYER_ID;
    }

    public String getPLAYER_NAME() {
        return PLAYER_NAME;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public List<ShotOnGoal> getSHOTS_ON_GOAL() {
        return SHOTS_ON_GOAL;
    }

    public List<Infraction> getINFRACTIONS() {
        return INFRACTIONS;
    }

    public int getNumYellowCards() {
        return numYellowCards;
    }

    public int getNumRedCards() {
        return numRedCards;
    }

    public int getNumPenaltyKicks() {
        return numPenaltyKicks;
    }

    public int getNumGoalsScored() {
        return numGoalsScored;
    }

    public int getCurrentYellow() {
        return currentYellow;
    }

    public int getCurrentRed() {
        return currentRed;
    }

    public int getCurrentPenalty() {
        return currentPenalty;
    }

    public int getCurrentGoals() {
        return currentGoals;
    }

    //constructor
    public Player(String name, int teamID) {
        this.PLAYER_NAME = name;
        this.teamID = teamID;
    }

    //methods
    public void startMatch() {
        currentYellow = 0;
        currentRed = 0;
        currentPenalty = 0;
        currentGoals = 0;
    }

    public void endMatch() {
        numYellowCards += currentYellow;
        numRedCards += currentRed;
        numPenaltyKicks += numPenaltyKicks;
        numGoalsScored += currentGoals;
    }

    public void shoots(LocalDateTime time, boolean scored, int MATCH_ID) {
        if(scored) currentGoals++;
        SHOTS_ON_GOAL.add(new ShotOnGoal(time, scored, PLAYER_ID, MATCH_ID));
    }

    public void commitsInfraction(char type, LocalDateTime time, int matchID) throws Exception {
        switch (type) {
            case 'y':
                currentYellow++;
                break;
            case 'r':
                currentRed++;
                break;
            case 'p':
                currentPenalty++;
                break;
            default: throw new Exception("invalid infraction type");
        }
        INFRACTIONS.add(new Infraction(time, type, PLAYER_ID, matchID));
    }
}
