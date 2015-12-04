package com.SocScore.android.app;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.SocScore.framework.AccessManager;
import com.SocScore.framework.data.LeagueAnalysis;
import com.SocScore.framework.data.Team;
import com.SocScore.framework.scorekeeper.BatchInput;

import java.util.ArrayList;
import java.util.List;

public class BatchInputActivity extends AppCompatActivity {

    private FloatingActionButton fab_create_new_team;
    BatchInput batchInput;
    private String str_team1;
    private String str_team2;
    private EditText et_team1;
    private EditText et_team2;
    private String str_team1_ID;
    private String str_team2_ID;
    Team team1;
    Team team2;
    private static List<Team> league = new ArrayList<>();
    private ListView listView;
    private final ArrayList<String> team_name_array = new ArrayList<>();
    private final ArrayList<Integer> team_ID_array = new ArrayList<>();
    private TextView match_title;
    private Dialog batch_input_dialog = null;
    private Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_input);
        context = BatchInputActivity.this;
        AccessManager.authenticate(1234);
        batch_input_dialog = new Dialog(context);
        setUpVariables();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_batch);
        league = LeagueAnalysis.getLeague();
        for (Team team : league) {
            team_name_array.add(team.getName());
        }
        for(Team team : league)
        {
            team_ID_array.add(team.getTEAM_ID());
        }
        TeamAdapter myAdapter = new TeamAdapter(league , BatchInputActivity.this);
        listView.setAdapter(myAdapter);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_league:
                        Intent leagueInput = new Intent(BatchInputActivity.this, LeagueInputActivity.class);
                        startActivity(leagueInput);
                        return true;

                    case R.id.action_live:
                        Intent liveInput = new Intent(BatchInputActivity.this, LiveMatchActivity.class);
                        startActivity(liveInput);
                        return true;

                    case R.id.action_main:
                        Intent batchInput = new Intent(BatchInputActivity.this, MainActivity.class);
                        startActivity(batchInput);
                        return true;

                    case R.id.access_analysis_viewer:
                        Intent analysisViewer = new Intent(BatchInputActivity.this, AnalysisViewerActivity.class);
                        startActivity(analysisViewer);
                        return true;
                    case R.id.add_all_matches_to_league:
                        addMatchesToLeague();

                    default:
                        // If we got here, the user's action was not recognized.
                        // Invoke the superclass to handle it.
                        return onMenuItemClick(menuItem);
                }
            }
        });
        fab_create_new_team = (FloatingActionButton) findViewById(R.id.fab_create_team);
        fab_create_new_team.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_team1.getText().toString().equalsIgnoreCase(null) || et_team2.getText().toString().equalsIgnoreCase(null) || et_team1.getText().toString().equalsIgnoreCase(et_team2.getText().toString()))
                {
                    Toast.makeText(getApplicationContext() , "Could not create match" , Toast.LENGTH_LONG).show();
                }
                else
                {
                    str_team1_ID = et_team1.getText().toString();
                    str_team2_ID = et_team2.getText().toString();
                    Intent batch_match_activity = new Intent(BatchInputActivity.this, BatchInputMatchActivity.class);
                    batch_match_activity.putExtra("str_team1_ID", str_team1_ID);
                    batch_match_activity.putExtra("str_team2_ID", str_team2_ID);
                    startActivity(batch_match_activity);
                }
                et_team1.setText("");
                et_team2.setText("");

            }
        });
    }

    public void addMatchesToLeague()
    {
        batchInput.addAllMatchesToLeague();
    }
    public void setUpVariables() {
        et_team1 = (EditText) findViewById(R.id.et_team1);
        et_team2 = (EditText) findViewById(R.id.et_team2);
        batchInput = new BatchInput();
        listView = (ListView) findViewById(R.id.list_view_score);
    }

    public static int findTeamID(String str) {
        for (Team team : league) {
            if (team.getName().equalsIgnoreCase(str)) {
                return team.getTEAM_ID();
            }
            throw new NullPointerException("Could not find team: " + str);
        }
        return -1;
    }

    public static boolean foundTeam(String str) {
        if (findTeamID(str) == -1) return false;
        return true;
    }

    private class TeamAdapter extends ArrayAdapter<Team>
    {
        private List<Team> league_team;
        private Context context;
        public TeamAdapter(List<Team> league , Context ctx)
        {
            super(ctx, R.layout.list_view_layout, league);
            this.league_team = league;
            this.context = ctx;
        }
        public View getView(int position , View convertView , ViewGroup parent)
        {
            if(convertView == null)
            {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_view_layout , parent , false);
            }
            TextView tv_name = (TextView) convertView.findViewById(R.id.team_name);
            TextView tv_id = (TextView) convertView.findViewById(R.id.team_id);
            Team team = league_team.get(position);
            tv_name.setText(team.getName());
            tv_id.setText("ID : " + team.getTEAM_ID());
            return convertView;
        }
    }
}
