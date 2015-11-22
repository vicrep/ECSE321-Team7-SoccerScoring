package com.SocScore.framework.data;

import java.util.ArrayList;
import java.util.Collections;
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

    public static void applyRank(PlayerRankType type) {
        switch(type) {
            case ID: Collections.sort(PLAYERS, Player.rankByID);
            case NAME: Collections.sort(PLAYERS, Player.rankByName);
            case GOALS: Collections.sort(PLAYERS, Player.rankByGoals);
            case REDCARDS: Collections.sort(PLAYERS, Player.rankByRedCards);
            case YELLOWCARDS: Collections.sort(PLAYERS, Player.rankByYellowCards);
            case PENALTY: Collections.sort(PLAYERS, Player.rankByPenalty);
            default: break;
        }
    }

}
