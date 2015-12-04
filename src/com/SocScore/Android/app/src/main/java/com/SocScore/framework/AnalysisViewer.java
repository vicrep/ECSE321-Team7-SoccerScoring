package com.SocScore.framework;

import com.SocScore.framework.data.LeagueAnalysis;
import com.SocScore.framework.data.PlayerAnalysis;
import com.SocScore.framework.data.TeamRankType;

import java.util.List;

/**
 * Object which allows the user to load saved data, and get various forms of this data.
 * Note that this object does not allow the modification or saving of loaded data.
 */
public class AnalysisViewer {

    /**
     * Loads saved data from disk
     * @see LeagueAnalysis#loadLeagueFromDisk()
     * @see PlayerAnalysis#loadPlayersFromDisk()
     */
    public void loadDataFromDisk() {
        LeagueAnalysis.loadLeagueFromDisk();
        PlayerAnalysis.loadPlayersFromDisk();
    }

    /**
     * @return ArrayList containing the {@link com.SocScore.framework.data.Team} in the League, sorted by ID
     */
    public List getLeague() {
        LeagueAnalysis.rankLeague(TeamRankType.ID);
        return LeagueAnalysis.getLeague();
    }

    /**
     * @see LeagueAnalysis#rankLeague(TeamRankType)
     * @param type the type of ranking the list of teams should be returned in
     * @return ArrayList of {@link com.SocScore.framework.data.Team} in the League
     */
    public List getLeague(TeamRankType type) {
        LeagueAnalysis.rankLeague(type);
        return LeagueAnalysis.getLeague();
    }

    /**
     * @return ArrayList of {@link com.SocScore.framework.data.Match} in League, sorted by ID
     */
    public List getMatchesByID() {
        LeagueAnalysis.sortMatchesByID();
        return LeagueAnalysis.getMatches();
    }

    /**
     * @return ArrayList of {@link com.SocScore.framework.data.Match} in League, sorted by most recent
     */
    public List getMatchesByDate() {
        LeagueAnalysis.sortMatchesByDate();
        return LeagueAnalysis.getMatches();
    }

    /**
     *
     * @param teamID {@link com.SocScore.framework.data.Team} for which you want the list of matches returned
     * @return ArrayList of {@link com.SocScore.framework.data.Match} that have been played by the inputted team
     */
    public List getMatchesForTeam(int teamID) {
        return LeagueAnalysis.getMatchesForTeam(teamID);
    }
}
