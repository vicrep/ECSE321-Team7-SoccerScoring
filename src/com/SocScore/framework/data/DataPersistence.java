package com.SocScore.framework.data;

import com.thoughtworks.xstream.XStream;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class DataPersistence {
    private static XStream xstream = new XStream();
    private static FileReader reader = null;
    private static FileWriter writer = null;
    static {
        xstream.alias("Player", Player.class);
        xstream.alias("Team", Team.class);
        xstream.alias("Match", Match.class);
        xstream.alias("ShotOnGoal", ShotOnGoal.class);
        xstream.alias("Infraction", Infraction.class);
        xstream.useAttributeFor(Player.class, "PLAYER_NAME");
        xstream.aliasField("name", Player.class, "PLAYER_NAME");
        xstream.useAttributeFor(Player.class, "PLAYER_ID");
        xstream.aliasField("ID", Player.class, "PLAYER_ID");
        xstream.useAttributeFor(Team.class, "name");
        xstream.aliasField("name", Team.class, "name");
        xstream.useAttributeFor(Team.class, "TEAM_ID");
        xstream.aliasField("ID", Team.class, "TEAM_ID");
        xstream.useAttributeFor(Match.class, "MATCH_ID");
        xstream.aliasField("ID", Match.class, "MATCH_ID");
    }


    public static void saveToDisk(String fileName, List in) {
        writer = null;
        try {
            writer = new FileWriter(fileName);
            String out = xstream.toXML(in);
            writer.write(out);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            if(writer!=null) {
                try {
                    writer.close();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ArrayList loadFromDisk(String fileName) {
        try {
            reader = new FileReader(fileName);
            return (ArrayList)xstream.fromXML(reader);
        }
        catch(Exception e) {
            System.err.println("Error in XML Read: " + e.getMessage());
            return null;
        }
    }
}
