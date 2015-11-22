package com.SocScore.framework.data;

import java.time.*;

public class Infraction {

    private LocalDateTime time;
    private InfractionType infractionType;
    private int playerID;
    private int matchID;

    public Infraction(LocalDateTime time, InfractionType infractionType, int playerID, int matchID) {
        this.time = time;
        this.infractionType = infractionType;
        this.playerID = playerID;
        this.matchID = matchID;
    }
}
