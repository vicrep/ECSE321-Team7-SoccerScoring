package com.SocScore.framework.data;

import org.joda.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Object representing a single player, and all their relevant fields.
 */
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

    /**
     * Creates a new instance of a player, assigning it to a {@link Team}.
     * Note that for coherence purposes, there is no way to add a player without a team.
     * @param name The name of the player to be created (doesn't have to be unique).
     * @param teamID The ID of the team the new player belongs too.
     */
    public Player(String name, int teamID) {
        this.PLAYER_NAME = name;
        this.teamID = teamID;
    }

    //methods

    /**
     * Called by {@link Team#startMatch()} when a {@link Match} is started.
     * This initializes the number of (match specific) infractions and goals to 0.
     */
    public void startMatch() {
        currentYellow = 0;
        currentRed = 0;
        currentPenalty = 0;
        currentGoals = 0;
    }

    /**
     * Called by {@link Team#endMatch(int, int)} when a {@link Match} is ended.
     * This transfers all the match specific infractions and goals to the player's permanent fields.
     */
    public void endMatch() {
        numYellowCards += currentYellow;
        numRedCards += currentRed;
        numPenaltyKicks += numPenaltyKicks;
        numGoalsScored += currentGoals;
    }

    /**
     * Adds a new instance of a {@link ShotOnGoal} to the players list of shots on goal.
     * @param time The time at which the shot on goal was performed.
     * @param scored Set to true when the attempt was successful (i.e. the player scored).
     * @param MATCH_ID The ID of the {@link Match} in which the shot on goal was performed.
     */
    public void shoots(LocalDateTime time, boolean scored, int MATCH_ID) {
        if(scored) currentGoals++;
        SHOTS_ON_GOAL.add(new ShotOnGoal(time, scored, PLAYER_ID, MATCH_ID));
    }

    /**
     * Adds a new instance of a {@link Infraction} to the players list of infractions.
     * @param type The type of infraction committed.
     * @param time The time at which the infraction was committed.
     * @param MATCH_ID The ID of the {@link Match} in which the infraction was committed.
     */
    public void commitsInfraction(InfractionType type, LocalDateTime time, int MATCH_ID) {
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
        INFRACTIONS.add(new Infraction(time, type, PLAYER_ID, MATCH_ID));
    }

    //comparators (for ranking)
    public static Comparator<Player> rankByID = new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            return p1.getPLAYER_ID() - p2.getPLAYER_ID();
        }
    };

    public static Comparator<Player> rankByName = new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            return p1.getPLAYER_NAME().compareTo(p2.getPLAYER_NAME());
        }
    };

    public static Comparator<Player> rankByGoals = new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            return p1.getNumGoalsScored() - p2.getNumGoalsScored();
        }
    };

    public static Comparator<Player> rankByRedCards = new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            return p1.getNumRedCards() - p2.getNumRedCards();
        }
    };

    public static Comparator<Player> rankByYellowCards = new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            return p1.getNumYellowCards() - p2.getNumYellowCards();
        }
    };

    public static Comparator<Player> rankByPenalty = new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            return p1.getNumPenaltyKicks() - p2.getNumPenaltyKicks();
        }
    };

    //setters and getters

    /**
     * @return The (unique) ID of the player.
     */
    public int getPLAYER_ID() {
        return PLAYER_ID;
    }

    /**
     * @return The name of the player.
     */
    public String getPLAYER_NAME() {
        return PLAYER_NAME;
    }

    /**
     * @return The ID of the {@link Team} which the player belongs to.
     */
    public int getTeamID() {
        return teamID;
    }

    /**
     * @see com.SocScore.framework.LeagueInput#transferPlayer(int, int, int)
     * @param teamID ID of the new {@link Team} the player is to be assigned to.
     */
    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    /**
     * @return ArrayList of {@link ShotOnGoal} performed by the player.
     */
    public List getSHOTS_ON_GOAL() {
        return SHOTS_ON_GOAL;
    }

    /**
     * @return ArrayList of {@link Infraction} committed by the player.
     */
    public List getINFRACTIONS() {
        return INFRACTIONS;
    }

    /**
     * @return The number of yellow cards the player has accumulated. Does not include yellow cards received in a currently happening match.
     */
    public int getNumYellowCards() {
        return numYellowCards;
    }

    /**
     * @return The number of red cards the player has accumulated. Does not include red cards received in a currently happening match.
     */
    public int getNumRedCards() {
        return numRedCards;
    }

    /**
     * @return The number of penalty kicks the player has accumulated. Does not include penalty kicks received in a currently happening match.
     */
    public int getNumPenaltyKicks() {
        return numPenaltyKicks;
    }

    /**
     * @return The number of goals the player has scored. Does not include goals scored in a currently happening match.
     */
    public int getNumGoalsScored() {
        return numGoalsScored;
    }

    /**
     * @return The number of yellow cards received during a currently happening match.
     */
    public int getCurrentYellow() {
        return currentYellow;
    }

    /**
     * @return The number of red cards received during a currently happening match.
     */
    public int getCurrentRed() {
        return currentRed;
    }

    /**
     * @return The number of penalty kicks received during a currently happening match.
     */
    public int getCurrentPenalty() {
        return currentPenalty;
    }

    /**
     * @return The number of goals scored during a currently happening match.
     */
    public int getCurrentGoals() {
        return currentGoals;
    }
}
