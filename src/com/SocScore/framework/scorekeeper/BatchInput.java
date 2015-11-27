package com.SocScore.framework.scorekeeper;

import com.SocScore.framework.data.LeagueAnalysis;
import com.SocScore.framework.data.Match;
import com.SocScore.framework.data.Team;

import org.joda.time.LocalDateTime;
/**
 * Object which manages creating, editing, and saving of matches in a batch context.
 * Also allows to edit matches stored in {@link LeagueAnalysis} (i.e. matches which have already happened)
 */
public class BatchInput extends ScoreKeeper {

    /**
     * creates a new match and adds it to {@link #MATCHES}. It also assigns this newly created match to {@link #currentMatch}
     * @see Match#Match(Team, Team, LocalDateTime, LocalDateTime)
     * @param team1 First team to add to match.
     * @param team2 Second team to add to match.
     * @param startTime Start time of the match.
     * @param endTime End time of the match.
     * @throws RuntimeException Thrown if either of the teams are null, or if the teams are identical.
     */
    public void createMatch(Team team1, Team team2, LocalDateTime startTime, LocalDateTime endTime) throws RuntimeException {
        if(team1 == team2 || team1 == null || team2 == null) throw new RuntimeException("Cannot add an empty team or duplicate teams to a match");
        currentMatch = new Match(team1, team2, startTime, endTime);
        MATCHES.add(currentMatch);
        hasUnsavedMatches = true;
    }

    /**
     * Updates {@link #currentMatch} with the appropriate fields
     * @see Match#updateScore()
     */
    public void saveMatch() {
        currentMatch.updateScore();
    }

    /**
     * Saves ({@link Match#updateScore()}) and then adds all matches to {@link LeagueAnalysis}, and removes them from {@link #MATCHES}.
     */
    public void addAllMatchesToLeague() {
        int numberOfMatchesToSave = MATCHES.size();
        for(int i = 0; i < numberOfMatchesToSave; i++) {
            MATCHES.get(i).updateScore();
            transferMatchToLeague(MATCHES.get(i));
        }
        currentMatch = null;
    }

    /**
     * Allows to load a match from {@link LeagueAnalysis}, add it to {@link #MATCHES}, set it as {@link #currentMatch} and edit it.
     * @param ID The ID of the match being loaded.
     */
    public void loadMatchFromLeague(int ID) {
        currentMatch = LeagueAnalysis.findMatch(ID);
        MATCHES.add(currentMatch);
        LeagueAnalysis.removeMatch(currentMatch);
        hasUnsavedMatches = true;
    }
}
