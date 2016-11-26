package projects.brainiacs.formtest.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import projects.brainiacs.formtest.DeportesService;
import projects.brainiacs.formtest.Models.Equipo;
import projects.brainiacs.formtest.Models.Evento;
import projects.brainiacs.formtest.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMatchActivity extends AppCompatActivity {

    private Spinner spinnerEvento;
    private Spinner spinnerEquipo1;
    private Spinner spinnerEquipo2;
    private Spinner spinnerHorario;

    private Button btnAnhadir;

    DeportesService deportesService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match);

        deportesService = DeportesService.retrofit.create(DeportesService.class);

        spinnerEvento = (Spinner)findViewById(R.id.spinnerEvento);
        final ArrayAdapter<String> spinnerEventoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerEvento.setAdapter(spinnerEventoAdapter);

        spinnerEquipo1 = (Spinner)findViewById(R.id.spinnerEquipo1);
        ArrayAdapter<String> spinnerEquipo1Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerEvento.setAdapter(spinnerEquipo1Adapter);

        spinnerEquipo2 = (Spinner)findViewById(R.id.spinnerEquipo2);
        ArrayAdapter<String> spinnerEquipo2Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        //spinnerEventoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEvento.setAdapter(spinnerEquipo2Adapter);

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
                Toast.makeText(AddMatchActivity.this, "Error en la conexión. Por favor intente nuevamente" /*+ t.getMessage()*/, Toast.LENGTH_LONG).show();
            }
        });




        spinnerEquipo1Adapter.add("value");
        spinnerEquipo1Adapter.notifyDataSetChanged();

        spinnerEquipo2Adapter.add("value");
        spinnerEquipo2Adapter.notifyDataSetChanged();


        btnAnhadir = (Button) findViewById(R.id.btnAnhadirPartido);

        btnAnhadir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

            }
        });
    }
}
