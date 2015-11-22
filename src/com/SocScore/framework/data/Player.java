package com.SocScore.framework.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    public void commitsInfraction(InfractionType type, LocalDateTime time, int matchID) {
        switch (type) {
            case YELLOW_CARD:
                currentYellow++;
                break;
            case RED_CARD:
                currentRed++;
                break;
            case PENALTY:
                currentPenalty++;
                break;
            default: break;
        }
        INFRACTIONS.add(new Infraction(time, type, PLAYER_ID, matchID));
    }

    //comparators (for ranking)
    public static Comparator<Player> rankByID = (p1, p2) -> p1.getPLAYER_ID() - p2.getPLAYER_ID();

    public static Comparator<Player> rankByName = (p1, p2) -> p1.getPLAYER_NAME().compareTo(p2.getPLAYER_NAME());

    public static Comparator<Player> rankByGoals = (p1, p2) -> p1.getNumGoalsScored() - p2.getNumGoalsScored();

    public static Comparator<Player> rankByRedCards = (p1, p2) -> p1.getNumRedCards() - p2.getNumRedCards();

    public static Comparator<Player> rankByYellowCards = (p1, p2) -> p1.getNumYellowCards() - p2.getNumYellowCards();

    public static Comparator<Player> rankByPenalty = (p1, p2) -> p1.getNumPenaltyKicks() - p2.getNumPenaltyKicks();

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
}
