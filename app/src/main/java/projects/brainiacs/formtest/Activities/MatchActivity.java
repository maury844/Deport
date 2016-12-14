package projects.brainiacs.formtest.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import projects.brainiacs.formtest.DeportesService;
import projects.brainiacs.formtest.Models.Equipo;
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
        setTitle("Universidad Privada Boliviana");

        Intent intent = getIntent();
        boolean permisosAdministrador = intent.getExtras().getBoolean("permisosAdministrador");

        listaPartidos = (ListView) findViewById(R.id.listViewPartidos);
        deportesService = DeportesService.retrofit.create(DeportesService.class);

        //Si es un administrador permitimos hacer click, sino no



        if(permisosAdministrador)
        {
            listaPartidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<? > arg0, View view, int position, long id) {
                    // When clicked, show a toast with the TextView text
                    Toast.makeText(getApplicationContext(), "item " + position + "   " + arg0.getItemAtPosition(position).toString(),
                            Toast.LENGTH_SHORT).show();

                    Partido partidoClick = (Partido) arg0.getItemAtPosition(position);

                    String fechaPartido = partidoClick.getFecha();

                    //Verifica que la fecha no sea pasada
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                    Date strDate;

                    try
                    {
                        strDate = sdf.parse(fechaPartido);
                        //Si es que la fecha actual, es despues de la fecha que queremos registrar el resultado, lo permitimos
                        if (new Date().after(strDate))
                        {
                            Intent intent = new Intent(MatchActivity.this, AddResultActivity.class);
                            intent.putExtra("idPartido", partidoClick.getIdPartido());
                            intent.putExtra("equipo1", partidoClick.getEquipo1());
                            intent.putExtra("equipo2", partidoClick.getEquipo2());
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(MatchActivity.this, "No puede agregar resultado de un partido !", Toast.LENGTH_LONG).show();
                        }

                    } catch (ParseException e)
                    {
                        e.printStackTrace();
                    }


                }

            });
        }

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
                Toast.makeText(MatchActivity.this, "Por favor intente nuevamente" /*+ t.getMessage()*/, Toast.LENGTH_LONG).show();
            }
        });


        Calendar c = Calendar.getInstance();

        final Partido p1 = new Partido(new Equipo("Sistemas").toString(), new Equipo("Telecom").toString(), "18:40", c.getTime().toString(), "1");
        final Partido p2 = new Partido(new Equipo("Administracion de empresas").toString(), new Equipo("Derecho").toString(), "19:20", c.getTime().toString(), "22");
        final Partido p3 = new Partido(new Equipo("Petrolera y Gas Natural").toString(), new Equipo("Ing Comercial").toString(), "20:00", c.getTime().toString(), "333");

        List<Partido> pTest = new ArrayList<Partido>();
        pTest.add(p1);
        pTest.add(p2);
        pTest.add(p3);

        PartidosAdapter adapter = new PartidosAdapter(getApplicationContext(), pTest);
        listaPartidos.setAdapter(adapter);

    }

}
