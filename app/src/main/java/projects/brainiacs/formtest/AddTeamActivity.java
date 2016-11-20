package projects.brainiacs.formtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class AddTeamActivity extends AppCompatActivity {

    Button  btnCreateTeam;
    EditText txtTeamName;
    Spinner spinnerSports;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* ??????????????
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        btnCreateTeam = (Button) findViewById(R.id.btnCreateTeam);
        txtTeamName = (EditText) findViewById(R.id.txtTeamName);
        spinnerSports = (Spinner) findViewById(R.id.spinnerSports);


        btnCreateTeam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                String teamName = txtTeamName.getText().toString();

                //Adding the Year to the team's name
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);


                //Validate the team name (?)
                if(teamName.length() > 0)
                {
                    Team team = new Team();
                    team.setTeamName(teamName + Integer.toString(year) /*+ sport*/);
                    //team.setSport
                }

                Toast.makeText(AddTeamActivity.this, "team", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
