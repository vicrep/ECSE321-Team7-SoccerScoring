package com.SocScore.framework.data;

import com.SocScore.framework.ScoreKeeper;

import java.util.ArrayList;
import java.util.List;

public class LeagueAnalysis {
    private static List<Team> league = new ArrayList<>();
    private static List<Match> matches = new ArrayList<>();

    public static void addTeam(Team team) {
        league.add(team);
    }

    public static void removeMatch(Match match) {
        matches.remove(match);
    }

    public static void addMatch(Match match) {
        matches.add(match);
    }

    public static void removeTeam(Team team) {
        league.remove(team);
    }

    public static Team findTeam(int ID) throws RuntimeException {
        for(Team team : league) {
                if(team.getTEAM_ID() == ID) return team;
        }
        throw new NullPointerException("Could not find team under provided ID: " + ID);
    }

    public static void rankLeague(TeamRankType type) {
        switch(type) {
            case ID: league.sort(Team.rankByID);
            case TEAM_SCORE: league.sort(Team.rankByScore);
            case TOTAL_GOALS: league.sort(Team.rankByTotalGoals);
            default: break;
        }
    }

    public static Match findMatch(int ID) throws RuntimeException {
        for (Match match : matches) {
            if(match.getMATCH_ID() == ID) return match;
        }
        throw new NullPointerException("Could not find match under provided ID: " + ID);
    }

    public static void saveLeagueToDisk() throws RuntimeException {
        if(ScoreKeeper.hasUnsavedMatches())
            throw new RuntimeException("Cannot save to disk until all matches have been transferred from ScoreKeeping to League");
        DataPersistence.saveToDisk("league.xml", league);
        DataPersistence.saveToDisk("matches.xml", matches);
    }

    public static void loadLeagueFromDisk() {
        league = DataPersistence.loadFromDisk("league.xml");
        matches = DataPersistence.loadFromDisk("matches.xml");
        league.forEach(Team::resetPlayers);
        matches.forEach(Match::resetTeams);
    }

    public static List getLeague() {
        return league;
    }

    public static List getMatches() {
        return matches;
    }
}
