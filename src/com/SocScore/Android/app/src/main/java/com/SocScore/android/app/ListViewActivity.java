package com.SocScore.android.app;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.SocScore.framework.data.Team;

import java.util.List;

public class ListViewActivity extends ListActivity {

    static final String[] COUNTRIES = new String[] {

            "Afghanistan", "Albania", "Algeria", "American Samoa",
            "Andorra", "Angola", "Anguilla", "Antarctica",
            "Antigua and Barbuda", "Argentina", "Armenia", "Aruba",
            "Australia", "Austria", "Azerbaijan", "Bahrain",
            "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize",
            "Benin", "Bermuda", "Bhutan", "Bolivia",
            "Bosnia and Herzegovina", "Botswana", "Bouvet Island",
            "Brazil", "British Indian Ocean Territory"
    };
    private List <Team> rank;



    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rank = (List<Team>) getIntent().getSerializableExtra("rank_team_list");
        ArrayAdapter<Team> arrayAdapter = new ArrayAdapter<Team>(this , android.R.layout.simple_list_item_1 , rank);

        getListView().setTextFilterEnabled(true);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);

        new AlertDialog.Builder(this)
                .setTitle("Hello")
                .setMessage("from " + getListView().getItemAtPosition(position))
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {}
                        })
                .show();

        Toast.makeText(ListViewActivity.this,
                "ListView: " + l.toString() + "\n" +
                        "View: " + v.toString() + "\n" +
                        "position: " + String.valueOf(position) + "\n" +
                        "id: " + String.valueOf(id),
                Toast.LENGTH_LONG).show();
    }

}