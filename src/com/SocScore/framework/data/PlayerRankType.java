package com.SocScore.framework.data;

/**
 * Enum which provides the possible ways in which players can be ranked.
 * @see PlayerAnalysis#applyRank(PlayerRankType)
 */
public enum PlayerRankType {
    ID, NAME, GOALS, REDCARDS, YELLOWCARDS, PENALTY
}
