package com.SocScore.framework.data;

import java.time.*;

public class ShotOnGoal {
    private LocalDateTime time;
    private boolean scored;
    private int playerID;
    private int matchID;


    public ShotOnGoal(LocalDateTime time, boolean scored, int playerID, int matchID) {
        this.time = time;
        this.scored = scored;
        this.playerID = playerID;
        this.matchID = matchID;
    }

}
