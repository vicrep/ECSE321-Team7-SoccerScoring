package com.SocScore.framework.data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.util.ArrayList;
import java.util.List;

public class LeagueAnalysis {
    private static List<Team> league = new ArrayList<>();
    private static List<Match> matches = new ArrayList<>();
    private static XStream xstream = new XStream(new StaxDriver());

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

    public static void saveLeagueToDisk() {
        DataPersistence.saveToDisk("league.xml", league);
        DataPersistence.saveToDisk("matches.xml", matches);
    }

    public static void loadLeagueFromDisk() {

    }
}
