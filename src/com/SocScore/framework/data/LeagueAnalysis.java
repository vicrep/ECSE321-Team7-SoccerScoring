package com.SocScore.framework.data;

import java.util.ArrayList;
import java.util.List;

public class LeagueAnalysis {
    private static final List<Team> LEAGUE = new ArrayList<>();
    private static final List<Match> MATCHES = new ArrayList<>();

    public static void addTeam(Team team) {
        LEAGUE.add(team);
    }

    public static void addMatch(Match match) {
        MATCHES.add(match);
    }

    public static void removeTeam(Team team) {
        LEAGUE.remove(team);
    }

    public static Team findTeam(int ID) throws RuntimeException {
        for (Team team : LEAGUE) {
            if (team.getTEAM_ID() == ID) return team;
        }
        throw new NullPointerException("Could not find team under provided ID: " + ID);
    }


}
