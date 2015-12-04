package com.SocScore.framework.scorekeeper;

import com.SocScore.framework.data.*;

import org.joda.time.LocalDateTime;

/**
 * Object which manages creating, controlling, and saving matches in a live context
 */
public class LiveInput extends ScoreKeeper {

    /**
     * Creates a new match, adds it to {@link #MATCHES}, and sets it as the {@link #currentMatch}.
     * @param team1 First team being added.
     * @param team2 Second team being added.
     * @throws RuntimeException throws an exception if either of the two teams being added are null, or are the same team.
     */
    public void createMatch(Team team1, Team team2) throws RuntimeException {
        if(team1 == team2 || team1 == null || team2 == null) throw new RuntimeException("Cannot add an empty team or duplicate teams to a match");
        currentMatch = new Match(team1, team2);
        getMATCHES().add(getCurrentMatch());
        hasUnsavedMatches = true;
    }

    /**
     * Starts a new match, and sets the {@link Match#startTime} to the current time.
     * @see Match#startMatch(LocalDateTime)
     * @throws RuntimeException Throws an exception if the match has already started, or has ended.
     */
    public void startMatch() throws RuntimeException {
        currentMatch.startMatch(new LocalDateTime());
    }

    /**
     * Ends a match, sets the {@link Match#endTime}, updates relevant fields, and transfers the match to the League.
     * @see Match#endMatch(LocalDateTime)
     * @see #transferMatchToLeague(Match)
     * @throws RuntimeException Throws an exception if the match has not been started with {@link #startMatch()}
     */
    public void endMatch() throws RuntimeException {
        currentMatch.endMatch(new LocalDateTime());
        transferMatchToLeague(getCurrentMatch());
    }

    /**
     * Calls {@link #shoots(int, boolean, LocalDateTime)} with the current time
     */
    public void shoots(int playerID, boolean scored) throws RuntimeException {
        shoots(playerID, scored, new LocalDateTime());
    }

    /**
     * Calls {@link #addInfraction(int, InfractionType, LocalDateTime)} with the current time
     */
    public void addInfraction(int playerID, InfractionType type) {
        addInfraction(playerID, type, new LocalDateTime());
    }
}
