package projects.brainiacs.formtest.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import projects.brainiacs.formtest.Models.Equipo;
import projects.brainiacs.formtest.R;


public class AddTeamActivity extends AppCompatActivity {

    Button btnCrearEquipo;
    EditText txtNombreEquipo;
    Spinner spinnerDeportes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        btnCrearEquipo = (Button) findViewById(R.id.btnCrearEquipo);
        txtNombreEquipo = (EditText) findViewById(R.id.txtNombreEquipo);
        spinnerDeportes = (Spinner) findViewById(R.id.spinnerDeportes);


        btnCrearEquipo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                String nombreEquipo = txtNombreEquipo.getText().toString();

                //Adding the Year to the team's name
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);


                //Validate the team name (?)
                if(nombreEquipo.length() > 0)
                {
                    //Equipo equipo = new Equipo();
                    //equipo.setNombre(nombreEquipo + Integer.toString(year) /*+ sport*/);
                    //team.setSport
                }

                Toast.makeText(AddTeamActivity.this, "Equipo", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
