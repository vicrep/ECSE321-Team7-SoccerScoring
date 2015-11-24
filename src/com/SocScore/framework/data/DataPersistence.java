package com.SocScore.framework.data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataPersistence {
    private static XStream xstream = new XStream();
    private static FileReader reader;
    private static FileWriter writer;
    static {
        xstream.registerConverter(new LocalDateTimeConverter());
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
        xstream.omitField(Player.class, "currentYellow");
        xstream.omitField(Player.class, "currentRed");
        xstream.omitField(Player.class, "currentPenalty");
        xstream.omitField(Player.class, "currentGoals");
        xstream.omitField(Match.class, "team1");
        xstream.omitField(Match.class, "team2");
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
        reader = null;
        try {
            reader = new FileReader(fileName);
            return (ArrayList)xstream.fromXML(reader);
        }
        catch(Exception e) {
            System.err.println("Error in XML Read: " + e.getMessage());
            return null;
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class LocalDateTimeConverter extends AbstractSingleValueConverter {

    public boolean canConvert(Class type) {
        return (type!=null) && LocalDateTime.class.getPackage().equals(type.getPackage());
    }

    public String toString (Object source) {
        return source.toString();
    }

    public Object fromString(String str) {
        try {
            return LocalDateTime.parse(str);
        }
        catch (Exception e) {
            return null;
        }
    }

}
