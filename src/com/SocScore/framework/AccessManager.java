package com.SocScore.framework;

import com.SocScore.framework.scorekeeper.BatchInput;
import com.SocScore.framework.scorekeeper.LiveInput;
import com.SocScore.framework.scorekeeper.ScoreKeeperType;

/**
 * Static class which manages granting access to data editing capabilities
 */
public class AccessManager {

    private static boolean isAuth = false;
    private static final int PIN = 1234;
    private static final AnalysisViewer leagueInput = new LeagueInput();
    private static final AnalysisViewer liveInput = new LiveInput();
    private static final AnalysisViewer batchInput = new BatchInput();

    /**
     * Compares input against a stored access code, and sets user as Authenticated if they match.
     * @param input The access code to be checked.
     * @return Returns true if the access code was accepted, false otherwise
     */
    public static boolean authenticate(int input) {
        isAuth = PIN == input;
        return isAuth;
    }

    /**
     * Unauthenticates user.
     * @return returns a {@link AnalysisViewer} instance (no data manipulation rights)
     */
    public static AnalysisViewer unAuthenticate() {
        if(isAuth) {
            isAuth = false;
            return new AnalysisViewer();
        }
        else throw new RuntimeException("User is already unauthenticated");
    }

    /**
     * Verifies that the user has successfully been authenticated with {@link #authenticate(int)}.
     * If so, the method returns an instance of the data-input objects based on the type requested.
     * @see LeagueInput
     * @see LiveInput
     * @see BatchInput
     * @param type type of data-edit instance requested
     * @return An instance of the data-input object requested. (defaults to {@link AnalysisViewer})
     */
    public static AnalysisViewer setInputType(ScoreKeeperType type) {
        if(!isAuth) throw new RuntimeException("User is not authorized as a ScoreKeeper");
        switch(type) {
            case LEAGUE_INPUT: return leagueInput;
            case LIVE_INPUT: return liveInput;
            case BATCH_INPUT: return batchInput;
            default: return new AnalysisViewer();
        }
    }
}
