package com.SocScore.android.app;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.SocScore.framework.AccessManager;
import com.SocScore.framework.data.InfractionType;
import com.SocScore.framework.data.LeagueAnalysis;
import com.SocScore.framework.data.Player;
import com.SocScore.framework.data.Team;
import com.SocScore.framework.scorekeeper.LiveInput;
import com.SocScore.framework.scorekeeper.ScoreKeeperType;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

public class LiveMatchActivity extends AppCompatActivity {
    private Chronometer chrono;
    private String str_team1;
    private String str_team2;
    private Context context = null;
    private Dialog dialog = null;
    private ImageButton close_dialog;
    private EditText add_player_to_team;
    private Button add_to_team1;
    private Button add_to_team2;
    private Team team1;
    private Team team2;
    private LiveInput liveInput;
    private TextView team1_score;
    private TextView team2_score;
    private Button increment_score;
    private RadioButton rd_radio_shots;
    private RadioButton rd_radio_yellow;
    private RadioButton rd_radio_red;
    private RadioButton rd_radio_penalty;
    private RadioGroup add_feature;
    private static int count1;
    private static int count2;
    private List<Player> players1 = new ArrayList<>();
    private List<Player> players2 = new ArrayList<>();
    private Spinner player_spinner_Team1;
    private Spinner player_spinner_Team2;
    private String str_player_name1;
    private String str_player_name2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_match);
        setUpVariables();
        count1 = 1;
        count2 = 1;
        chrono.start();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_live);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_league:
                        Intent leagueInput = new Intent(LiveMatchActivity.this, LeagueInputActivity.class);
                        startActivity(leagueInput);
                        return true;

                    case R.id.action_main:
                        Intent liveInput = new Intent(LiveMatchActivity.this, MainActivity.class);
                        startActivity(liveInput);
                        return true;

                    case R.id.action_batch:
                        Intent batchInput = new Intent(LiveMatchActivity.this, BatchInputActivity.class);
                        startActivity(batchInput);
                        return true;

                    case R.id.access_analysis_viewer:
                        Intent analysisViewer = new Intent(LiveMatchActivity.this, AnalysisViewerActivity.class);
                        startActivity(analysisViewer);
                        return true;

                    default:
                        // If we got here, the user's action was not recognized.
                        // Invoke the superclass to handle it.
                        return onMenuItemClick(menuItem);
                }
            }
        });
        int int_team1_ID = Integer.parseInt(getIntent().getStringExtra("str_team1_ID"));
        int int_team2_ID = Integer.parseInt(getIntent().getStringExtra("str_team2_ID"));
        team1 = LeagueAnalysis.findTeam(int_team1_ID);
        team2 = LeagueAnalysis.findTeam(int_team2_ID);
        players1 = team1.getPlayers();
        players2 = team2.getPlayers();
        player_spinner_Team1.setAdapter(new SpinnerAdapter(LiveMatchActivity.this, R.layout.custom_spinner, players1));
        player_spinner_Team2.setAdapter(new SpinnerAdapter(LiveMatchActivity.this, R.layout.custom_spinner, players2));
        playerSpinnerTeam1();
        playerSpinnerTeam2();
        str_team1 = team1.getName();
        str_team2 = team2.getName();
        if(team1.getPlayers().size() < 11 || team2.getPlayers().size() < 11)
        {
            Toast.makeText(getApplicationContext() , "There are not enough players in either one of the selected teams" , Toast.LENGTH_LONG).show();
        }
        else
        {
            liveInput.createMatch(team1, team2);
            liveInput.startMatch();
        }
    }

    public void setUpVariables() {
        liveInput = new LiveInput();
        AccessManager.authenticate(1234);
        liveInput= (LiveInput) AccessManager.setInputType(ScoreKeeperType.LIVE_INPUT);
        chrono = (Chronometer) findViewById(R.id.chronometer);
        team1_score = (TextView) findViewById(R.id.tv_team1_score);
        team2_score = (TextView) findViewById(R.id.tv_team2_score);
        increment_score = (Button) findViewById(R.id.increment_score_team1);
        rd_radio_shots = (RadioButton) findViewById(R.id.radio_shots);
        rd_radio_yellow = (RadioButton) findViewById(R.id.radio_yellow);
        rd_radio_red = (RadioButton) findViewById(R.id.radio_red);
        rd_radio_penalty = (RadioButton) findViewById(R.id.radio_penalty);
        add_feature = (RadioGroup) findViewById(R.id.add_feature);
        player_spinner_Team1 = (Spinner) findViewById(R.id.spinner_player_team1);
        player_spinner_Team2 = (Spinner) findViewById(R.id.spinner_player_team2);


    }

    public void playerSpinnerTeam1()
    {
        player_spinner_Team1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Player player = (Player) player_spinner_Team1.getSelectedItem();
                str_player_name1 = player.getPLAYER_NAME();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void playerSpinnerTeam2()
    {
        player_spinner_Team2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Player player = (Player) player_spinner_Team2.getSelectedItem();
                str_player_name2 = player.getPLAYER_NAME();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void incrementScoreTeam1(View view)
    {
        List<Player> players_team1 = team1.getPlayers();
        for(Player player : players_team1)
        {
            if(str_player_name1.equalsIgnoreCase(player.getPLAYER_NAME()))
            {
                if(player.getNumRedCards() == 1)
                {
                    Toast.makeText(getApplicationContext(), player.getPLAYER_NAME() + " is expelled from match, can't add a shot", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int i = count1++;
                    team1_score.setText("" + i);
                    liveInput.shoots(player.getPLAYER_ID() , true , new LocalDateTime());
                }
            }
        }
    }

    public void assignFeatureToPlayer1(View view) {
        List<Player> players_team1 = team1.getPlayers();
        int selectedID = add_feature.getCheckedRadioButtonId();
        switch (selectedID)
        {
            case (R.id.radio_shots):
                for (Player player : players_team1) {
                    if (str_player_name1.equalsIgnoreCase(player.getPLAYER_NAME())) {
                        if(player.getNumRedCards() == 1)
                        {
                            Toast.makeText(getApplicationContext(), player.getPLAYER_NAME() + " is expelled from match, can't add a shot", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            liveInput.shoots(player.getPLAYER_ID(), false, new LocalDateTime());
                            Toast.makeText(getApplicationContext(), player.getPLAYER_NAME() + " took his chance and shot!! Unfortunately he did not score", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            case (R.id.radio_yellow):
                for (Player player : players_team1) {
                    if (str_player_name1.equalsIgnoreCase(player.getPLAYER_NAME())) {
                        if(player.getNumYellowCards() == 0)
                        {
                            liveInput.addInfraction(player.getPLAYER_ID(), InfractionType.YELLOW_CARD, new LocalDateTime());
                            Toast.makeText(getApplicationContext(), "Yellow card assigned to " + player.getPLAYER_NAME(), Toast.LENGTH_SHORT).show();
                        }
                        else if(player.getNumYellowCards() == 1)
                        {
                            liveInput.addInfraction(player.getPLAYER_ID(), InfractionType.YELLOW_CARD, new LocalDateTime());
                            liveInput.addInfraction(player.getPLAYER_ID(), InfractionType.RED_CARD, new LocalDateTime());
                            Toast.makeText(getApplicationContext(), "2nd yellow card assigned to " + player.getPLAYER_NAME() + ". He then gets a red card too", Toast.LENGTH_SHORT).show();
                        }
                        else if(player.getNumYellowCards() == 2)
                        {
                            Toast.makeText(getApplicationContext(), player.getPLAYER_NAME() + " is expelled from match, can't add a yellow card", Toast.LENGTH_SHORT).show();

                        }

                    }
                }
                break;
            case (R.id.radio_red):
                for (Player player : players_team1) {
                    if (str_player_name1.equalsIgnoreCase(player.getPLAYER_NAME())) {
                        if(player.getNumRedCards() == 1)
                        {
                            Toast.makeText(getApplicationContext(), player.getPLAYER_NAME() + " is expelled from match, can't add a red card", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            liveInput.addInfraction(player.getPLAYER_ID(), InfractionType.RED_CARD, new LocalDateTime());
                            Toast.makeText(getApplicationContext(), "Red card assigned to " + player.getPLAYER_NAME(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            case (R.id.radio_penalty):
                for (Player player : players_team1) {
                    if (str_player_name1.equalsIgnoreCase(player.getPLAYER_NAME())) {
                        if (player.getNumRedCards() == 1) {
                            Toast.makeText(getApplicationContext(), player.getPLAYER_NAME() + " is expelled from match, can't add a penalty", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            liveInput.addInfraction(player.getPLAYER_ID(), InfractionType.PENALTY, new LocalDateTime());
                            Toast.makeText(getApplicationContext(), "Penalty assigned to " + player.getPLAYER_NAME(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
        }

    }

    public void endMatch(View view)
    {
        liveInput.endMatch();
        Intent main = new Intent(this , MainActivity.class);
        startActivity(main);
    }

    public void incrementScoreTeam2(View view)
    {
        List<Player> players_team2 = team2.getPlayers();
        for(Player player : players_team2)
        {
            if(str_player_name2.equalsIgnoreCase(player.getPLAYER_NAME()))
            {
                if(player.getNumRedCards() == 1)
                {
                    Toast.makeText(getApplicationContext(), player.getPLAYER_NAME() + " is expelled from match, can't add a shot", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int j = count2++;
                    team2_score.setText("" + j);
                    liveInput.shoots(player.getPLAYER_ID(), true, new LocalDateTime());
                }

            }
        }
    }

    public void assignFeatureToPlayer2(View view)
    {
        List<Player> players_team2 = team2.getPlayers();
        int selectedID = add_feature.getCheckedRadioButtonId();
        switch (selectedID)
        {
            case (R.id.radio_shots):
                for (Player player : players_team2) {
                    if (str_player_name2.equalsIgnoreCase(player.getPLAYER_NAME())) {
                        if(player.getNumRedCards() == 1)
                        {
                            Toast.makeText(getApplicationContext(), player.getPLAYER_NAME() + " is expelled from match, can't add a shot", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            liveInput.shoots(player.getPLAYER_ID(), false, new LocalDateTime());
                            Toast.makeText(getApplicationContext(), player.getPLAYER_NAME() + " took his chance and shot!! Unfortunately he did not score", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            case (R.id.radio_yellow):
                for (Player player : players_team2) {
                    if (str_player_name2.equalsIgnoreCase(player.getPLAYER_NAME())) {
                        if(player.getNumYellowCards() == 0)
                        {
                            liveInput.addInfraction(player.getPLAYER_ID(), InfractionType.YELLOW_CARD, new LocalDateTime());
                            Toast.makeText(getApplicationContext(), "Yellow card assigned to " + player.getPLAYER_NAME(), Toast.LENGTH_SHORT).show();
                        }
                        else if(player.getNumYellowCards() == 1)
                        {
                            liveInput.addInfraction(player.getPLAYER_ID(), InfractionType.YELLOW_CARD, new LocalDateTime());
                            liveInput.addInfraction(player.getPLAYER_ID(), InfractionType.RED_CARD, new LocalDateTime());
                            Toast.makeText(getApplicationContext(), "2nd yellow card assigned to " + player.getPLAYER_NAME() + ". He then gets a red card too", Toast.LENGTH_SHORT).show();
                        }
                        else if(player.getNumYellowCards() == 2)
                        {
                            Toast.makeText(getApplicationContext(), player.getPLAYER_NAME() + " is expelled from match, can't add a yellow card", Toast.LENGTH_SHORT).show();

                        }

                    }
                }
                break;
            case (R.id.radio_red):
                for (Player player : players_team2) {
                    if (str_player_name2.equalsIgnoreCase(player.getPLAYER_NAME())) {
                        if(player.getNumRedCards() == 1)
                        {
                            Toast.makeText(getApplicationContext(), player.getPLAYER_NAME() + " is expelled from match, can't add a red card", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            liveInput.addInfraction(player.getPLAYER_ID(), InfractionType.RED_CARD, new LocalDateTime());
                            Toast.makeText(getApplicationContext(), "Red card assigned to " + player.getPLAYER_NAME(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            case (R.id.radio_penalty):
                for (Player player : players_team2) {
                    if (str_player_name2.equalsIgnoreCase(player.getPLAYER_NAME())) {
                        if (player.getNumRedCards() == 1) {
                            Toast.makeText(getApplicationContext(), player.getPLAYER_NAME() + " is expelled from match, can't add a penalty", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            liveInput.addInfraction(player.getPLAYER_ID(), InfractionType.PENALTY, new LocalDateTime());
                            Toast.makeText(getApplicationContext(), "Penalty assigned to " + player.getPLAYER_NAME(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
        }
    }

    public class SpinnerAdapter extends ArrayAdapter {
        private List<Player> players;
        private Context ctx;
        private int layout_id;
        public SpinnerAdapter(Context context, int textViewResourceId, List<Player> player_list) {
            super(context, textViewResourceId, player_list);
            this.ctx = context;
            this.players = player_list;
            this.layout_id = textViewResourceId;

        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View layout;
            layout = inflater.inflate(R.layout.custom_spinner_first_position, parent, false);
            final TextView tv_player_spinner = (TextView) layout.findViewById(R.id.tv_player_spinner);
            Player player = players.get(position);
            tv_player_spinner.setText(player.getPLAYER_NAME());
            tv_player_spinner.setTextColor(Color.BLACK);

            return layout;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }
    }
}
