package com.SocScore.framework.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerAnalysis {
    private static List<Player> players = new ArrayList<>();

    public static void addPlayer(Player player) {
        players.add(player);
    }

    public static void removePlayer(Player player) {
        players.remove(player);
    }

    public static Player findPlayer(int ID) throws NullPointerException {
        for (Player player : players) {
            if (player.getPLAYER_ID() == ID) return player;
        }
        throw new NullPointerException("Could not find player under provided ID: " + ID);
    }

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

    public static void savePlayersToDisk() {
        DataPersistence.saveToDisk("players.xml", players);
    }

    public static void loadPlayersFromDisk() {
        players = DataPersistence.loadFromDisk("players.xml");
    }
}
