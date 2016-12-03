package projects.brainiacs.formtest.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import projects.brainiacs.formtest.DeportesService;
import projects.brainiacs.formtest.EventosAdapter;
import projects.brainiacs.formtest.Models.Evento;
import projects.brainiacs.formtest.Models.Partido;
import projects.brainiacs.formtest.PartidosAdapter;
import projects.brainiacs.formtest.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventActivity extends AppCompatActivity {

    DeportesService deportesService;
    private ListView listaEventos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        listaEventos = (ListView) findViewById(R.id.listViewPartidos);
        deportesService = DeportesService.retrofit.create(DeportesService.class);


        Call<List<Evento>> call = deportesService.getEventos();

        call.enqueue(new Callback<List<Evento>>()
        {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {

                EventosAdapter adapter = new EventosAdapter(getApplicationContext(), response.body());
                listaEventos.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                Toast.makeText(EventActivity.this, "Por favor intente nuevamente" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });




    }
}
