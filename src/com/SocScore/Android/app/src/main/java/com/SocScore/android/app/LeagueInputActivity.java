package com.SocScore.android.app;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.SocScore.framework.AccessManager;
import com.SocScore.framework.LeagueInput;
import com.SocScore.framework.data.LeagueAnalysis;
import com.SocScore.framework.data.Player;
import com.SocScore.framework.data.Team;

import java.util.ArrayList;
import java.util.List;

public class LeagueInputActivity extends AppCompatActivity {

    private LeagueInput leagueInput = new LeagueInput();
    private EditText et_add_remove_team;
    private Button add_team;
    private Button remove_team;
    private String str_add_remove_team;
    private List<Team> league = new ArrayList<>();
    private Button add_player;
    private EditText add_player_name;
    private EditText add_player_ID;
    private String str_name;
    private int ID;
    private EditText et_transfer_player_id;
    private EditText et_old_team_id;
    private EditText et_new_team_id;
    private Button transfer_player;
    private int int_transfer_player_id;
    private int int_old_team_id;
    private int int_new_team_id;
    private EditText et_remove_player_id;
    private Button remove_player;
    private int int_remove_player_id;
    private EditText et_remove_match_id;
    private Button remove_match;
    private int int_remove_match_id;
    private Spinner spinner_team;
    private Team team;
    private int team_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_input);
        setUpVariables();
        AccessManager.authenticate(1234);
        league = LeagueAnalysis.getLeague();
        spinner_team.setAdapter(new SpinnerAdapter(LeagueInputActivity.this , R.layout.custom_spinner , league));
        spinnerTeam();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_league);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_batch:
                        Intent leagueInput = new Intent(LeagueInputActivity.this, BatchInputActivity.class);
                        startActivity(leagueInput);
                        return true;

                    case R.id.action_main:
                        Intent batchInput = new Intent(LeagueInputActivity.this, MainActivity.class);
                        startActivity(batchInput);
                        return true;

                    case R.id.access_analysis_viewer:
                        Intent analysisViewer = new Intent(LeagueInputActivity.this, AnalysisViewerActivity.class);
                        startActivity(analysisViewer);
                        return true;

                    case R.id.action_save_data:
                        saveData();
                        return true;

                    case R.id.action_load_data:
                        loadData();
                        return true;


                    default:
                        // If we got here, the user's action was not recognized.
                        // Invoke the superclass to handle it.
                        return onMenuItemClick(menuItem);
                }
            }
        });

    }
    public void saveData()
    {
        leagueInput.saveDataToDisk();
    }

    public void loadData()
    {
        leagueInput.loadDataFromDisk();
    }

    public void spinnerTeam()
    {
        spinner_team.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                team = (Team) spinner_team.getSelectedItem();
                team_id = team.getTEAM_ID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setUpVariables()
    {
        et_add_remove_team = (EditText) findViewById(R.id.et_add_remove_team);
        add_team = (Button) findViewById(R.id.add_team);
        remove_team = (Button) findViewById(R.id.remove_team);
        add_player = (Button) findViewById(R.id.add_player);
        add_player_name = (EditText) findViewById(R.id.et_add_player_name);
        et_transfer_player_id = (EditText) findViewById(R.id.et_transfer_player);
        et_old_team_id = (EditText) findViewById(R.id.et_old_team_id);
        et_new_team_id = (EditText) findViewById(R.id.et_new_team_id);
        transfer_player = (Button) findViewById(R.id.transfer_player);
        et_remove_player_id = (EditText) findViewById(R.id.et_remove_player);
        remove_player = (Button) findViewById(R.id.remove_player);
        et_remove_match_id = (EditText) findViewById(R.id.et_match_id);
        remove_match = (Button)  findViewById(R.id.remove_match);
        spinner_team = (Spinner) findViewById(R.id.spinner_teams);
    }

    public void addTeamToLeague(View view)
    {
        str_add_remove_team = et_add_remove_team.getText().toString();

        for(Team team : league)
        {
            if (str_add_remove_team.equalsIgnoreCase(team.getName()))
            {
                Toast.makeText(getApplicationContext(), "Team is already in league" , Toast.LENGTH_LONG).show();
                return;
            }

        }
        if(str_add_remove_team.matches(""))
        {
            Toast.makeText(getApplicationContext(), "Field is empty, enter a team's name please", Toast.LENGTH_LONG).show();
            return;
        }
        leagueInput.addTeamToLeague(str_add_remove_team);
        et_add_remove_team.setText("");
        finish();
        startActivity(getIntent());
    }

    public int findTeamID(String str)
    {
        for(Team team : league)
        {
            if(team.getName().equalsIgnoreCase(str))
            {
                return team.getTEAM_ID();
            }
            Toast.makeText(getApplicationContext(), "Could not find team: " + str, Toast.LENGTH_LONG).show();

        }
        return -1;
    }

    public boolean foundTeam(String str)
    {
        if (findTeamID(str) == -1)
        {
            return false;
        }
        return true;
    }

    public void removeTeamFromLeague(View view)
    {
        str_add_remove_team = et_add_remove_team.getText().toString();
        if(foundTeam(str_add_remove_team))
        {
            leagueInput.removeTeamFromLeague(findTeamID(str_add_remove_team));
            Toast.makeText(getApplicationContext() , "Team was removed from league" , Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Could not find team: " + str_add_remove_team, Toast.LENGTH_LONG).show();
        }
        et_add_remove_team.setText("");
    }

    public void addPlayerToTeam(View view)
    {
        str_name = add_player_name.getText().toString();
        List<Player> players = LeagueAnalysis.findTeam(team_id).getPlayers();
        for (Player player : players)
        {
            if(str_name.equalsIgnoreCase(player.getPLAYER_NAME()))
            {
                Toast.makeText(getApplicationContext() , "Player is already in the selected team" , Toast.LENGTH_LONG).show();
                return;
            }
        }
        if(str_name.matches(""))
        {
            Toast.makeText(getApplicationContext(), "Field is empty, enter a player's name please", Toast.LENGTH_LONG).show();
            return;
        }
        leagueInput.addNewPlayerToTeam(str_name, team_id);
        Toast.makeText(getApplicationContext() , "Player was added to corresponding team" , Toast.LENGTH_SHORT).show();
        add_player_name.setText("");
    }

    public void transferPlayer(View view)
    {
        int_transfer_player_id = Integer.parseInt(et_transfer_player_id.getText().toString());
        int_old_team_id = Integer.parseInt(et_old_team_id.getText().toString());
        int_new_team_id = Integer.parseInt(et_new_team_id.getText().toString());
        leagueInput.transferPlayer(int_transfer_player_id, int_old_team_id, int_new_team_id);
        Toast.makeText(getApplicationContext() , "Player was transferred" , Toast.LENGTH_SHORT).show();
        et_transfer_player_id.setText("");
        et_old_team_id.setText("");
        et_new_team_id.setText("");
    }

    public void removePlayerFromLeague(View view)
    {
        int_remove_player_id = Integer.parseInt(et_remove_player_id.getText().toString());
        leagueInput.removePlayerFromLeague(int_remove_player_id);
        Toast.makeText(getApplicationContext() , "Player was removed from league" , Toast.LENGTH_SHORT).show();
        et_remove_player_id.setText("");
    }

    public void removeMatchFromLeague(View view)
    {
        int_remove_match_id = Integer.parseInt(et_remove_match_id.getText().toString());
        leagueInput.removeMatchFromLeague(int_remove_match_id);
        Toast.makeText(getApplicationContext() , "Match was removed from league" , Toast.LENGTH_SHORT).show();
        et_remove_match_id.setText("");
    }

    public class SpinnerAdapter extends ArrayAdapter {
        private List<Team> teams;
        private Context ctx;
        private int layout_id;
        public SpinnerAdapter(Context context, int textViewResourceId, List<Team> team_list) {
            super(context, textViewResourceId, team_list);
            this.ctx = context;
            this.teams = team_list;
            this.layout_id = textViewResourceId;

        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View layout;
            layout = inflater.inflate(R.layout.custom_spinner_first_position, parent, false);
            final TextView tv_player_spinner = (TextView) layout.findViewById(R.id.tv_player_spinner);
            Team team = teams.get(position);
            tv_player_spinner.setText(team.getName());
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
