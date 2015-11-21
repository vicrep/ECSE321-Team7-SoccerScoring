package com.SocScore.framework.data;

import java.time.*;

public class Infraction {

    private LocalDateTime time;
    private char infractionType;
    private int playerID;
    private int matchID;

    public Infraction(LocalDateTime time, char infractionType, int playerID, int matchID) {
        this.time = time;
        this.infractionType = infractionType;
        this.playerID = playerID;
        this.matchID = matchID;
    }
}
