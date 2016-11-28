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

    boolean invalidFields = false;

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

                invalidFields = false;

                if(isEmpty(txtNombreEquipo))
                {
                    txtNombreEquipo.setError("Campos Requeridos");
                    invalidFields = true;
                }
                else if( !isValid(txtNombreEquipo) )
                {
                    txtNombreEquipo.setError("El nombre no puede contener caracteres especiales");
                    invalidFields = true;
                }

                //El nombre del equipo es correcto
                if(!invalidFields)
                {
                    String nombreEquipo = txtNombreEquipo.getText().toString();
                }
/*
                //Adding the Year to the team's name
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
*/


            }
        });

    }


    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }

    public boolean isValid(EditText editText) {
        String w = editText.getText().toString();

        //Al menos una letra y luego una combinacion cualquiera de letras y numeros
        return w.matches("[a-zA-Z][a-zA-Z_0-9]*");
    }


}
