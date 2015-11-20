/**
 * Created by victor on 01/11/2015.
 */
public class Match {
    private final int matchID;
    private java.time.LocalDateTime startTime;
    private java.time.LocalDateTime endTime;
    private int team1ID;
    private int team2ID;
    private int winnerTeamID;
    private int team1Score;
    private int team2Score;
    private Team[] teams;

    public Match(Team team1, Team team2) {
        this.teams[0] = team1;
        this.teams[1] = team2;
    }
}
