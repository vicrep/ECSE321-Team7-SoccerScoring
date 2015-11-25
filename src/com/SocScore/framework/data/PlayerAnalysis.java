package com.SocScore.framework.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Static Object which stores and controls information pertaining to all the players in the league.
 */
public class PlayerAnalysis {
    private static List<Player> players = new ArrayList();

    /**
     * Allows to add a new player to the list of players in the league.
     * @param player Player object to be added.
     */
    public static void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Allows to remove a player from the list of players in the league.
     * @param player Player object to be removed.
     */
    public static void removePlayer(Player player) {
        players.remove(player);
    }

    /**
     * Allows to search for a player in the league based on its ID.
     * @param ID ID of player to be found.
     * @return Player object matching the inputted ID.
     * @throws NullPointerException Throws a NullPointerException if the player cannot be found in the league.
     */
    public static Player findPlayer(int ID) throws NullPointerException {
        for (Player player : players) {
            if (player.getPLAYER_ID() == ID) return player;
        }
        throw new NullPointerException("Could not find player under provided ID: " + ID);
    }

    /**
     * Applies a rank/sort on the players in the league.
     * @param type Type of ranking to apply.
     */
    public static void applyRank(PlayerRankType type) {
        switch(type) {
            case ID: Collections.sort(players, Player.rankByID);
            case NAME: Collections.sort(players, Player.rankByName);
            case GOALS: Collections.sort(players, Player.rankByGoals);
            case REDCARDS: Collections.sort(players, Player.rankByRedCards);
            case YELLOWCARDS: Collections.sort(players, Player.rankByYellowCards);
            case PENALTY: Collections.sort(players, Player.rankByPenalty);
            default: break;
        }
    }

    /**
     * Saves all the players in the league to a file.
     */
    public static void savePlayersToDisk() {
        DataPersistence.saveToDisk("players.xml", players);
    }

    /**
     * Loads players from file. Note that this overwrites any players previously stored in the league.
     */
    public static void loadPlayersFromDisk() {
        players = DataPersistence.loadFromDisk("players.xml");
        for(Player player : players) {
            int teamID = player.getTeamID();
            Team team = LeagueAnalysis.findTeam(teamID);
            team.addPlayer(player);
        }
    }

    /**
     * @return Returns the list of players in the league.
     */
    public List getPlayers() {
        return players;
    }
}
