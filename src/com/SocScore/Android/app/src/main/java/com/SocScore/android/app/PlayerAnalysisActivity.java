package com.SocScore.android.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PlayerAnalysisActivity extends AppCompatActivity {

    private RadioGroup player_ranking;
    private RadioButton radio_rank_id;
    private RadioButton radio_rank_name;
    private RadioButton radio_rank_goals;
    private RadioButton radio_rank_yellow;
    private RadioButton radio_rank_red;
    private RadioButton radio_rank_penalties;
    private Button btn_player_ranking;
    private ImageButton close_player_ranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_analysis);
        setUpVariables();
    }

    public void setUpVariables()
    {
        player_ranking = (RadioGroup) findViewById(R.id.player_ranks);
        radio_rank_id = (RadioButton) findViewById(R.id.rank_player_by_id);
        radio_rank_name = (RadioButton) findViewById(R.id.rank_player_by_name);
        radio_rank_goals = (RadioButton) findViewById(R.id.rank_player_by_goals);
        radio_rank_yellow = (RadioButton) findViewById(R.id.rank_player_by_yellow);
        radio_rank_red = (RadioButton) findViewById(R.id.rank_player_by_red);
        radio_rank_penalties = (RadioButton) findViewById(R.id.rank_player_by_penalties);
        btn_player_ranking = (Button) findViewById(R.id.btn_player_ranking);
        close_player_ranking = (ImageButton) findViewById(R.id.close_player_analysis);
    }

    public void closePlayerAnalysis(View view)
    {
        Intent intent = new Intent(this , AnalysisViewerActivity.class);
        startActivity(intent);
    }

    public void rankPlayers(View view)
    {
        int selectedID = player_ranking.getCheckedRadioButtonId();
        switch (selectedID)
        {
            case (R.id.rank_player_by_id) :
                Intent name = new Intent(PlayerAnalysisActivity.this , PlayerByIDActivity.class);
                startActivity(name);
                break;

            case (R.id.rank_player_by_name) :
                Intent id = new Intent(PlayerAnalysisActivity.this , PlayerByNameActivity.class);
                startActivity(id);
                break;

            case (R.id.rank_player_by_goals) :
                Intent goals = new Intent(PlayerAnalysisActivity.this , PlayerByGoalsActivity.class);
                startActivity(goals);
                break;

            case (R.id.rank_player_by_yellow) :
                Intent yellow = new Intent(PlayerAnalysisActivity.this , PlayerByYellowCardsActivity.class);
                startActivity(yellow);
                break;

            case (R.id.rank_player_by_red) :
                Intent red = new Intent(PlayerAnalysisActivity.this , PlayerByRedCardsActivity.class);
                startActivity(red);
                break;

            case (R.id.rank_player_by_penalties) :
                Intent penalties = new Intent(PlayerAnalysisActivity.this , PlayerByPenaltiesActivity.class);
                startActivity(penalties);
                break;
        }
    }
}
