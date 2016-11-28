package projects.brainiacs.formtest.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import okhttp3.ResponseBody;
import projects.brainiacs.formtest.DeportesService;
import projects.brainiacs.formtest.Models.Equipo;
import projects.brainiacs.formtest.Models.Evento;
import projects.brainiacs.formtest.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class AddTeamActivity extends AppCompatActivity {

    private Button btnCrearEquipo;
    private EditText txtNombreEquipo;
    private Spinner spinnerEventos;

    DeportesService deportesService;

    boolean invalidFields = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        btnCrearEquipo = (Button) findViewById(R.id.btnCrearEquipo);
        txtNombreEquipo = (EditText) findViewById(R.id.txtNombreEquipo);

        deportesService = DeportesService.retrofit.create(DeportesService.class);

        spinnerEventos = (Spinner) findViewById(R.id.spinnerEventos);
        final ArrayAdapter<String> spinnerEventoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerEventos.setAdapter(spinnerEventoAdapter);

        //Cargar los eventos al spinner
        Call<List<Evento>> callEventos = deportesService.getEventos();

        callEventos.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                for (Evento ev : response.body())
                {
                    spinnerEventoAdapter.add(ev.toString());
                }
                spinnerEventoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                Toast.makeText(AddTeamActivity.this, "No se pudo obtener los eventos.\n Por favor intente nuevamente" /*+ t.getMessage()*/, Toast.LENGTH_LONG).show();
            }
        });


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
                    Equipo equipo = new Equipo();
                    equipo.setNombre(txtNombreEquipo.getText().toString());
                    equipo.setEvento(spinnerEventos.getSelectedItem().toString());

                    Call<ResponseBody> callPostPartido = deportesService.postEquipo(equipo);

                    callPostPartido.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
                        {
                            //El servidor responde un OK a la insercion
                            Toast.makeText(AddTeamActivity.this, "Equipo registrado con éxito!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(AddTeamActivity.this, "Por favor intente nuevamente", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

    }


    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }

    public boolean isValid(EditText editText) {
        String w = editText.getText().toString();

        //Al menos una letra y luego una combinacion cualquiera de letras y numeros
        return w.matches("[a-zA-Z]([a-zA-Z_0-9 ])*");
    }


}
