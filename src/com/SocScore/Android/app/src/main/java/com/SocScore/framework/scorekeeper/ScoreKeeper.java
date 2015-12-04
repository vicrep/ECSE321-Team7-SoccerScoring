package com.SocScore.framework.scorekeeper;

import com.SocScore.framework.AnalysisViewer;
import com.SocScore.framework.data.*;

import org.joda.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class which contains methods shared between the Match Input classes.
 * @see LiveInput
 * @see BatchInput
 */
public abstract class ScoreKeeper extends AnalysisViewer {
    /**
     * ArrayList of Match objects, used to temporarily store matches being monitored by the Score Keeping instance using it.
     * Note that these matches are not stored in the league until you call a transfer method on them.
     * @see LiveInput#endMatch()
     * @see BatchInput#addAllMatchesToLeague()
     */
    final List<Match> MATCHES = new ArrayList<>();
    /**
     * Allows for easy access to the match that is being edited
     * @see #selectMatch(int)
     */
    Match currentMatch;

    /**
     * Used to ensure no data is left in the instance before performing a save to disk.
     * Set to true when the Score Keeping instance is still editing matches.
     */
    static boolean hasUnsavedMatches = false;

    /**
     * Sets {@link #currentMatch} to a match being controlled by the Score Keeping Instance
     * @param i index of match in {@link #MATCHES} that is being set.
     */
    public void selectMatch(int i) {
        currentMatch = MATCHES.get(i);
    }


    /**
     * Used to register that a player in {@link #currentMatch} has attempted a shot at the goal.
     * If the attempt is successful, the appropriate team's score is automatically updated. In both situations,
     * a {@link ShotOnGoal} instance is added to the player's {@link Player#SHOTS_ON_GOAL};
     * @param playerID ID of the player who performed the shot on Goal.
     * @param scored Set to true if a goal was scored.
     * @param time Time at which the action was performed.
     * @throws RuntimeException Thrown when player is not in the teams in {@link #currentMatch}.
     */
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

    /**
     * Used to register that a player in {@link #currentMatch} has committed an infraction.
     * This adds the infraction to the player's {@link Player#INFRACTIONS}.
     * @param playerID ID of the player who performed the infraction.
     * @param type Type of infraction committed.
     * @param time Time at which the infraction was committed.
     */
    public void addInfraction(int playerID, InfractionType type, LocalDateTime time) {
        Player player = PlayerAnalysis.findPlayer(playerID);
        if(currentMatch.playerIsInMatch(player)) {
            player.commitsInfraction(type, time, currentMatch.getMATCH_ID());
        }
        else throw new RuntimeException("Cannot add an infraction to a player who isn't currently in the match");
    }

    /**
     * Transfers a completed match from the Score Keeping instance's {@link #MATCHES} to {@link LeagueAnalysis#matches}
     * @see BatchInput#addAllMatchesToLeague()
     * @see LiveInput#endMatch()
     * @param match Match object to be transferred.
     */
    void transferMatchToLeague(Match match) {
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
