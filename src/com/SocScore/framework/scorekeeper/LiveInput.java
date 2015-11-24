package com.SocScore.framework.scorekeeper;

import com.SocScore.framework.data.*;

import java.time.LocalDateTime;

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
     * Starts a new match, and sets the {@link Match#startTime}
     * @throws RuntimeException throws an exception if the match has already started, or has ended.
     */
    public void startMatch() throws RuntimeException {
        currentMatch.startMatch(LocalDateTime.now());
    }

    public void endMatch() throws RuntimeException {
        currentMatch.endMatch(LocalDateTime.now());
        transferMatchToLeague(getCurrentMatch());
    }

    public void shoots(int playerID, boolean scored) throws RuntimeException {
        shoots(playerID, scored, LocalDateTime.now());
    }

    public void addInfraction(int playerID, InfractionType type) {
        addInfraction(playerID, type, LocalDateTime.now());
    }


}
