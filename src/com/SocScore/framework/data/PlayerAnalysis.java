package com.SocScore.framework.data;

import java.util.ArrayList;
import java.util.List;

public class PlayerAnalysis {
    private static final List<Player> PLAYERS = new ArrayList<>();

    public static void addPlayer(Player player) {
        PLAYERS.add(player);
    }

    public static void removePlayer(Player player) {
        PLAYERS.remove(player);
    }

    public static Player findPlayer(int ID) throws NullPointerException {
        for (Player player : PLAYERS) {
            if (player.getPLAYER_ID() == ID) return player;
        }
        throw new NullPointerException("Could not find player under provided ID: " + ID);
    }

}
