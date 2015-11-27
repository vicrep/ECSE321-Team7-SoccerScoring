package com.SocScore.framework.data;

import org.joda.time.LocalDateTime;
/**
 * Object which stores the instance of an attempted shot on a goal.
 */
public class ShotOnGoal {
    private final LocalDateTime time;
    private final boolean scored;
    private final int playerID;
    private final int matchID;


    /**
     * Creates a new instance of shot on goal, setting the necessary fields.
     * @param time Time at which the goal attempt was made.
     * @param scored Set to true if the goal was successful (i.e. the player scored).
     * @param playerID The ID of the {@link Player} who performed the shot on goal.
     * @param matchID The ID of the {@link Match} in which the shot on goal was performed.
     */
    public ShotOnGoal(LocalDateTime time, boolean scored, int playerID, int matchID) {
        this.time = time;
        this.scored = scored;
        this.playerID = playerID;
        this.matchID = matchID;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public boolean isScored() {
        return scored;
    }

    public int getPlayerID() {
        return playerID;
    }

    public int getMatchID() {
        return matchID;
    }
}
