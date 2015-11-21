package com.SocScore.framework;

public class AccessManager {

    private static boolean isAuth = false;
    static final int PIN = 1234;

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

    public static AnalysisViewer setInputType(char type) throws Exception {
        if(!isAuth) throw new RuntimeException("User is not authorized as a ScoreKeeper");
        switch(type) {
            case 's': return new ScoreKeeper();
            case 'l': return new LiveInput();
            case 'b': return new BatchInput();
        }
        throw new Exception("Invalid ScoreKeeper type selected: " + type);
    }
}
