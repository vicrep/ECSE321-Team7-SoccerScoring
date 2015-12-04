package com.SocScore.framework.data;

import org.joda.time.LocalDateTime;

/**
 * Object which stores an instance of an infraction committed by a {@link Player}.
 * @see Player#commitsInfraction(InfractionType, LocalDateTime, int)
 * @see com.SocScore.framework.scorekeeper.ScoreKeeper#addInfraction(int, InfractionType, LocalDateTime)
 */
public class Infraction {
    private final LocalDateTime time;
    private final InfractionType infractionType;
    private final int playerID;
    private final int matchID;

    /**
     * Creates a new infraction and initializes all its constants (all fields are final).
     * @param time Time at which the infraction was committed.
     * @param infractionType Type of infraction which was committed.
     * @param playerID ID of {@link Player} who committed the infraction.
     * @param matchID ID of the match in which this infraction occurred.
     */
    public Infraction(LocalDateTime time, InfractionType infractionType, int playerID, int matchID) {
        this.time = time;
        this.infractionType = infractionType;
        this.playerID = playerID;
        this.matchID = matchID;
    }

    /**
     * @return Returns the date and time at which the infraction occurred.
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * @return Returns the type of infraction which was committed.
     */
    public InfractionType getInfractionType() {
        return infractionType;
    }

    /**
     * @return Returns the ID of the {@link Player} who committed the infraction.
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * @return Returns the ID of the {@link Match} in which the infraction occurred.
     */
    public int getMatchID() {
        return matchID;
    }
}
