package com.SocScore.android.app;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.SocScore.framework.data.LeagueAnalysis;
import com.SocScore.framework.data.Team;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Dialog live_input_dialog = null;
    private Context context = null;
    private Button create_new_match_live_button;
    private Button league_input;
    private EditText add_team1;
    private EditText add_team2;
    private Button submitTeamLive;
    private ImageButton close_input_dialog;
    private String str_team1_ID;
    private String str_team2_ID;
    private ListView listView;
    private static List<Team> league = new ArrayList<>();
    private final ArrayList<Integer> team_ID_array = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        live_input_dialog = new Dialog(context);
        setUpVariables();
        setUpDialog();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_main);
        league = LeagueAnalysis.getLeague();
        TeamAdapter teamAdapter = new TeamAdapter(league , MainActivity.this);
        listView.setAdapter(teamAdapter);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_league:
                        Intent leagueInput = new Intent(MainActivity.this, LeagueInputActivity.class);
                        startActivity(leagueInput);
                        return true;

                    case R.id.action_live:
                        Intent liveInput = new Intent(MainActivity.this, LiveMatchActivity.class);
                        startActivity(liveInput);
                        return true;

                    case R.id.action_batch:
                        Intent batchInput = new Intent(MainActivity.this, BatchInputActivity.class);
                        startActivity(batchInput);
                        return true;

                    case R.id.access_analysis_viewer:
                        Intent analysisViewer = new Intent(MainActivity.this , AnalysisViewerActivity.class);
                        startActivity(analysisViewer);
                        return true;

                    default:
                        // If we got here, the user's action was not recognized.
                        // Invoke the superclass to handle it.
                        return onMenuItemClick(menuItem);
                }
            }
        });
    }


    public void setUpDialog()
    {
        live_input_dialog.setContentView(R.layout.dialog_live_input);
        create_new_match_live_button = (Button) live_input_dialog.findViewById(R.id.create_new_match_button_live_dialog);
        add_team1 = (EditText) live_input_dialog.findViewById(R.id.et_team1);
        add_team2 = (EditText) live_input_dialog.findViewById(R.id.et_team2);
        listView = (ListView) live_input_dialog.findViewById(R.id.list_view_score);
    }

    public void setUpVariables()
    {
        create_new_match_live_button = (Button) findViewById(R.id.create_new_match_button_live_dialog);
        league_input = (Button) findViewById(R.id.league_input);
    }

    public void enterBatchInput(View view)
    {
        Intent batchMode = new Intent(this , BatchInputActivity.class);
        startActivity(batchMode);
    }

    public void enterLiveInput(View view)
    {
        live_input_dialog.show();
    }

    public void close_live_dialog(View view)
    {
        live_input_dialog.dismiss();
    }

    public void accessLeagueInput(View view)
    {
       Intent leagueInput = new Intent(this , LeagueInputActivity.class);
        startActivity(leagueInput);
    }

    public void createNewMatch(View view)
    {
        for(Team team : league)
        {
            team_ID_array.add(team.getTEAM_ID());
        }
        str_team1_ID = add_team1.getText().toString();
        str_team2_ID = add_team2.getText().toString();
        Intent live_match_activity = new Intent(this, LiveMatchActivity.class);
        live_match_activity.putExtra("str_team1_ID" , str_team1_ID);
        live_match_activity.putExtra("str_team2_ID" , str_team2_ID);
        live_input_dialog.dismiss();
        startActivity(live_match_activity);
    }

    public void accessAnalysisViewer(View view)
    {
        Intent analysisViewer = new Intent(this , AnalysisViewerActivity.class);
        startActivity(analysisViewer);
    }

    private class TeamAdapter extends ArrayAdapter<Team>
    {
        private List<Team> league_team;
        private Context context;
        private int layout_id;
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
