package projects.brainiacs.formtest.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import projects.brainiacs.formtest.DeportesService;
import projects.brainiacs.formtest.Models.Partido;
import projects.brainiacs.formtest.PartidosAdapter;
import projects.brainiacs.formtest.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchActivity extends AppCompatActivity {

    DeportesService deportesService;
    private ListView listaPartidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listaPartidos = (ListView) findViewById(R.id.listViewPartidos);
        deportesService = DeportesService.retrofit.create(DeportesService.class);


        Call<List<Partido>> call = deportesService.getPartidos();

        call.enqueue(new Callback<List<Partido>>()
        {
            @Override
            public void onResponse(Call<List<Partido>> call, Response<List<Partido>> response) {

                PartidosAdapter adapter = new PartidosAdapter(getApplicationContext(), response.body());
                listaPartidos.setAdapter(adapter);

             }

            @Override
            public void onFailure(Call<List<Partido>> call, Throwable t) {
                Toast.makeText(MatchActivity.this, "Por favor intente nuevamente" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

/*
        Calendar c = Calendar.getInstance();

        final Partido p1 = new Partido(new Equipo("Sistemas"), new Equipo("Telecom"), "18:40", c.getTime());
        final Partido p2 = new Partido(new Equipo("Sistemas 333LoMejor"), new Equipo("Telecom 3LolXD33"), "19:40", c.getTime());
        final Partido p3 = new Partido(new Equipo("Sistemas666"), new Equipo("Telecom666"), "19:10", c.getTime());

        List<Partido> pTest = new ArrayList<Partido>();
        pTest.add(p1);
        pTest.add(p2);
        pTest.add(p3);

        PartidosAdapter adapter = new PartidosAdapter(getApplicationContext(), pTest);
        listaPartidos.setAdapter(adapter);
*/
    }

}
