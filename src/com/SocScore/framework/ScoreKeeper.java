package com.SocScore.framework;

import com.SocScore.framework.data.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class which contains methods shared between the Match Input classes.
 * @see LiveInput
 * @see BatchInput
 */
public abstract class ScoreKeeper extends AnalysisViewer {
    /**
     * ArrayList of Match objects, used to temporarily store matches being monitored by the Score Keeping class using it.
     * <p>
     *     Note that these matches are not stored in the league until you call a transfer method on them.
     * </p>
     * @see Match
     * @see LiveInput#endMatch()
     * @see BatchInput#addAllMatchesToLeague()
     */
    final List<Match> MATCHES = new ArrayList<>();
    Match currentMatch;
    static boolean hasUnsavedMatches = false;

    public void selectMatch(int i) {
        currentMatch = MATCHES.get(i);
    }

    public void shoots(int playerID, boolean scored, LocalDateTime time) throws RuntimeException {
        Player player = PlayerAnalysis.findPlayer(playerID);
        if(currentMatch.playerIsInMatch(player)) {
            if(scored) {
                currentMatch.incrementTeamScore(player.getTeamID());
            }
            player.shoots(time, scored, currentMatch.getMATCH_ID());
        }
        else throw new RuntimeException("Cannot add a goal from a player who isn't currently in the match");
    }

    public void addInfraction(int playerID, InfractionType type, LocalDateTime time) {
        Player player = PlayerAnalysis.findPlayer(playerID);
        if(currentMatch.playerIsInMatch(player)) {
            player.commitsInfraction(type, time, currentMatch.getMATCH_ID());
        }
        else throw new RuntimeException("Cannot add an infraction to a player who isn't currently in the match");
    }

    public void transferMatchToLeague(Match match) {
        LeagueAnalysis.addMatch(match);
        MATCHES.remove(match);
        hasUnsavedMatches = !MATCHES.isEmpty();
    }

    public List<Match> getMATCHES() {
        return MATCHES;
    }

    void setCurrentMatch(Match match) {
        this.currentMatch = match;
    }

    public Match getCurrentMatch() {
        return currentMatch;
    }

    public static boolean hasUnsavedMatches() {
        return hasUnsavedMatches;
    }
}
