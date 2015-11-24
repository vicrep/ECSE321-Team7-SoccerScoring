package com.SocScore.framework.data;

import com.SocScore.framework.scorekeeper.ScoreKeeper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static void sortMatchesByID() {
        matches.sort(Match.sortByID);
    }

    public static void sortMatchesByDate() {
        matches.sort(Match.sortByTime);
    }

    public static List getMatchesForTeam(int teamID) throws RuntimeException {
        List<Match> searchList = matches.stream().filter(match -> match.getTEAM1_ID() == teamID || match.getTEAM2_ID() == teamID).collect(Collectors.toList());
        if(searchList.isEmpty()) throw new RuntimeException("Could not find any matches for Team ID: " + teamID);
        return searchList;
    }

    public static Match findMatch(int ID) throws RuntimeException {
        for (Match match : matches) {
            if(match.getMATCH_ID() == ID) return match;
        }
        throw new NullPointerException("Could not find match under provided ID: " + ID);
    }

    public static void saveLeagueToDisk() throws RuntimeException {
        if(ScoreKeeper.hasUnsavedMatches())
            throw new RuntimeException("Cannot save to disk until all matches have been transferred from Score Keeping instances to League");
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
