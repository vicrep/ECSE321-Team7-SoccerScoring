package com.SocScore.framework;

import com.SocScore.framework.data.LeagueAnalysis;
import com.SocScore.framework.data.PlayerAnalysis;
import com.SocScore.framework.data.TeamRankType;

import java.util.List;

public class AnalysisViewer {

    public void loadDataFromDisk() {
        LeagueAnalysis.loadLeagueFromDisk();
        PlayerAnalysis.loadPlayersFromDisk();
    }

    public List getLeague() {
        LeagueAnalysis.rankLeague(TeamRankType.ID);
        return LeagueAnalysis.getLeague();
    }

    public List getLeague(TeamRankType type) {
        LeagueAnalysis.rankLeague(type);
        return LeagueAnalysis.getLeague();
    }

    public List getMatchesByID() {
        LeagueAnalysis.sortMatchesByID();
        return LeagueAnalysis.getMatches();
    }

    public List getMatchesByDate() {
        LeagueAnalysis.sortMatchesByDate();
        return LeagueAnalysis.getMatches();
    }

    public List getMatchesForTeam(int teamID) {
        return LeagueAnalysis.getMatchesForTeam(teamID);
    }
}
