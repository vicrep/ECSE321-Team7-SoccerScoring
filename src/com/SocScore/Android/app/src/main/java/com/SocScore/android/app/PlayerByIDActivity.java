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

import com.SocScore.framework.data.Player;
import com.SocScore.framework.data.PlayerAnalysis;
import com.SocScore.framework.data.PlayerRankType;

import java.util.ArrayList;
import java.util.List;

public class PlayerByIDActivity extends AppCompatActivity {

    private ListView list_view_players;
    private List<Player> players = new ArrayList<>();
    private PlayerAnalysis playerAnalysis = new PlayerAnalysis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_by_id);
        setUpVariables();
        PlayerAdapter playerAdapter = new PlayerAdapter(players , PlayerByIDActivity.this);
        list_view_players.setAdapter(playerAdapter);
    }

    public void setUpVariables()
    {
        playerAnalysis.applyRank(PlayerRankType.ID);
        players = playerAnalysis.getPlayers();
        list_view_players = (ListView) findViewById(R.id.list_view_players_id);
    }

    public void closePlayerAnalysisID(View view)
    {
        Intent intent = new Intent(this , PlayerAnalysisActivity.class);
        startActivity(intent);
    }

    private class PlayerAdapter extends ArrayAdapter<Player>
    {
        private List<Player> players_league;
        private Context context;
        private int layout_id;
        public PlayerAdapter(List<Player> league , Context ctx)
        {
            super(ctx, R.layout.player_analysis_viewer_layout, league);
            this.players_league = league;
            this.context = ctx;
            this.layout_id = R.layout.player_analysis_viewer_layout;
        }
        public View getView(int position , View convertView , ViewGroup parent)
        {
            if(convertView == null)
            {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(layout_id , parent , false);
            }
            TextView tv_name = (TextView) convertView.findViewById(R.id.analysis_player_name);
            TextView tv_id = (TextView) convertView.findViewById(R.id.analysis_player_id);
            TextView tv_goals = (TextView) convertView.findViewById(R.id.analysis_player_goals);
            TextView tv_yellow = (TextView) convertView.findViewById(R.id.analysis_player_yellow_card);
            TextView tv_red = (TextView) convertView.findViewById(R.id.analysis_player_red_card);
            TextView tv_penalties = (TextView) convertView.findViewById(R.id.analysis_player_penalties);
            Player player = players_league.get(position);
            tv_name.setText(player.getPLAYER_NAME());
            tv_id.setText("" + player.getPLAYER_ID());
            tv_goals.setText("" + player.getNumGoalsScored());
            tv_yellow.setText("" + player.getNumYellowCards());
            tv_red.setText("" + player.getNumRedCards());
            tv_penalties.setText("" + player.getNumPenaltyKicks());
            return convertView;
        }
    }

}
