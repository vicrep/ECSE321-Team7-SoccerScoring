package com.SocScore.android.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.SocScore.framework.AnalysisViewer;
import com.SocScore.framework.data.Team;
import com.SocScore.framework.data.TeamRankType;

import java.util.ArrayList;
import java.util.List;

public class AnalysisViewerTeamScoreActivity extends AppCompatActivity
{
    private ListView listViewScore;
    private List league = new ArrayList<>();
    private AnalysisViewer analysisViewer = new AnalysisViewer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_viewer_team_score);
        setUpVariables();
        TeamAdapter teamAdapter = new TeamAdapter(league , AnalysisViewerTeamScoreActivity.this);
        listViewScore.setAdapter(teamAdapter);
    }

    public void setUpVariables()
    {
        league = analysisViewer.getLeague(TeamRankType.TEAM_SCORE);
        listViewScore = (ListView) findViewById(R.id.list_view_score);
    }


    public void closeAnalysisScore(View view) {
        Intent intent = new Intent(this , AnalysisViewerActivity.class);
        startActivity(intent);
    }

    private class TeamAdapter extends ArrayAdapter<Team>
    {
        private List<Team> league_team;
        private Context context;
        private int layout_id;
        public TeamAdapter(List<Team> league , Context ctx)
        {
            super(ctx, R.layout.analysis_viewer_layout, league);
            this.league_team = league;
            this.context = ctx;
            this.layout_id = R.layout.analysis_viewer_layout;
        }
        public View getView(int position , View convertView , ViewGroup parent)
        {
            if(convertView == null)
            {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(layout_id , parent , false);
            }
            TextView tv_name = (TextView) convertView.findViewById(R.id.analysis_team_name);
            TextView tv_id = (TextView) convertView.findViewById(R.id.analysis_team_id);
            TextView tv_score = (TextView) convertView.findViewById(R.id.analysis_team_score);
            TextView tv_goals = (TextView) convertView.findViewById(R.id.analysis_team_goals);
            Team team = league_team.get(position);
            tv_name.setText(team.getName());
            tv_id.setText("" + team.getTEAM_ID());
            tv_score.setText("" + team.getTeamScore());
            tv_goals.setText("" + team.getTotalGoalsScored());
            return convertView;
        }
    }

}
