package com.SocScore.android.app;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.SocScore.framework.AccessManager;
import com.SocScore.framework.AnalysisViewer;
import com.SocScore.framework.scorekeeper.BatchInput;
import com.SocScore.framework.scorekeeper.LiveInput;
import com.SocScore.framework.scorekeeper.ScoreKeeperType;

public class LoginActivity extends AppCompatActivity {

    private EditText password;
    private Button authenticate;
    private Button signing_up_dialog;
    private FloatingActionButton fab_sign_up;
    private ImageButton close_dialog_button;
    private TextView password_forgotten;
    private TextView internet_connection_error;
    private TextView error;
    private Dialog dialog = null;
    private Context context = null;
    private Button access_analysis_viewer_button;
    private AnalysisViewer analysisViewer = new AnalysisViewer();
    private LiveInput liveInput;
    private BatchInput batchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpVariables();
//        LeagueInput leagueInput = new LeagueInput();
//        leagueInput.loadDataFromDisk();
        context = LoginActivity.this;
        dialog = new Dialog(context);
        setUpDialog();
    }

    public void setUpVariables()
    {
        password = (EditText) findViewById(R.id.password);
        authenticate = (Button) findViewById(R.id.authentication_button);
        password_forgotten = (TextView) findViewById(R.id.password_forgotten);
        internet_connection_error = (TextView) findViewById(R.id.internet_connection_error);
        error = (TextView) findViewById(R.id.error);
        access_analysis_viewer_button = (Button) findViewById(R.id.access_analysis_viewer);
    }

    public void setUpDialog()
    {
        dialog.setContentView(R.layout.dialog_sign_up);
        dialog.setTitle("Sign up");
        signing_up_dialog = (Button) dialog.findViewById(R.id.signing_up);
        close_dialog_button = (ImageButton) dialog.findViewById(R.id.close_dialog);
    }

    public void password_forgotten(View view)
    {
        password_forgotten.setVisibility(View.VISIBLE);
        password_forgotten.setMovementMethod(LinkMovementMethod.getInstance());
    }

    //checks for available network
    private boolean isNetworkAvailable()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if((activeNetworkInfo != null) && activeNetworkInfo.isConnected())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //checks credentials
    public void userIsValid(String password_value)
    {

        if (password_value.equals("1234"))
        {
            int password = Integer.parseInt(password_value);
            AccessManager.authenticate(password);
            liveInput = (LiveInput)AccessManager.setInputType(ScoreKeeperType.LIVE_INPUT);
            goToMainActivity();
        }
        //displays error message for username or password
        else
        {
            error.setText("Incorrect password");
            password_forgotten(password_forgotten);
        }
    }



    public void goToMainActivity()
    {
        Intent mainActivity = new Intent(this , MainActivity.class);
        startActivity(mainActivity);
    }

    //When authentication button is clicked, checks credentials
    public void authentication(View view)
    {
        //TODO: create an http client
        //checks for available network
        if (isNetworkAvailable())
        {
            String password_value = password.getText().toString();
            userIsValid(password_value);
        }
        else
        {
            //displays error message for connection
            internet_connection_error.setText("Check Internet Connection");
        }
    }

    public void signing_up(View view)
    {
        Intent login_page = new Intent(this , LoginActivity.class);
        startActivity(login_page);
        signing_up_dialog.setBackgroundResource(R.drawable.sign_in_background_selected);
        //TODO: create a real AnalysisViewer
    }

    public void close_dialog(View view)
    {
        dialog.dismiss();
    }

    public void accessAnalysisViewer(View view)
    {
        Intent analysisViewer = new Intent(this , AnalysisViewerActivity.class);
        startActivity(analysisViewer);
    }
}

