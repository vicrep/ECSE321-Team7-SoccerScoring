package com.SocScore.framework;

import com.SocScore.framework.scorekeeper.BatchInput;
import com.SocScore.framework.scorekeeper.LiveInput;
import com.SocScore.framework.scorekeeper.ScoreKeeperType;

public class AccessManager {

    private static boolean isAuth = false;
    static final int PIN = 1234;
    static final AnalysisViewer leagueInput = new LeagueInput();
    static final AnalysisViewer liveInput = new LiveInput();
    static final AnalysisViewer batchInput = new BatchInput();

    static public boolean authenticate(int input) {
        if(PIN == input) isAuth = true;
        return isAuth;
    }

    static public AnalysisViewer unAuthenticate() {
        if(isAuth) {
            isAuth = false;
            return new AnalysisViewer();
        }
        else throw new RuntimeException("User is already unauthenticated");
    }

    public static AnalysisViewer setInputType(ScoreKeeperType type) {
        if(!isAuth) throw new RuntimeException("User is not authorized as a ScoreKeeper");
        switch(type) {
            case LEAGUE_INPUT: return leagueInput;
            case LIVE_INPUT: return liveInput;
            case BATCH_INPUT: return batchInput;
            default: return null;
        }
    }
}
