package com.SocScore.android.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MatchAnalysisActivity extends AppCompatActivity {

    private RadioGroup rank_matches;
    private RadioButton rank_matches_ID;
    private RadioButton rank_matches_date;
    private Button matches_ranking;
    private ImageButton close_matches_ranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_analysis);
        setUpVariables();
    }

    public void setUpVariables()
    {
        rank_matches = (RadioGroup) findViewById(R.id.rank_matches);
        rank_matches_ID = (RadioButton) findViewById(R.id.radio_matches_ID);
        rank_matches_date = (RadioButton) findViewById(R.id.radio_matches_date);
        matches_ranking = (Button) findViewById(R.id.submit_matches_ranking);
        close_matches_ranking = (ImageButton) findViewById(R.id.close_match_analysis);
    }


    public void closeMatchAnalysis(View view)
    {
        Intent intent = new Intent (this , AnalysisViewerActivity.class);
        startActivity(intent);
    }

    public void rankMatches(View view)
    {
        int selectedID = rank_matches.getCheckedRadioButtonId();
        switch (selectedID)
        {
            case (R.id.radio_matches_ID) :
                Intent matches_ID = new Intent(MatchAnalysisActivity.this , MatchByIDActivity.class);
                startActivity(matches_ID);
                break;

            case (R.id.radio_matches_date) :
                Intent matches_date = new Intent(MatchAnalysisActivity.this , MatchByDateActivity.class);
                startActivity(matches_date);
                break;
        }
    }
}
