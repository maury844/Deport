package projects.brainiacs.formtest.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import projects.brainiacs.formtest.DeportesService;
import projects.brainiacs.formtest.Models.Equipo;
import projects.brainiacs.formtest.Models.Evento;
import projects.brainiacs.formtest.Models.Partido;
import projects.brainiacs.formtest.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMatchActivity extends AppCompatActivity {

    private Spinner spinnerEvento;
    private Spinner spinnerEquipo1;
    private Spinner spinnerEquipo2;
    private Spinner spinnerHorario;

    private DatePicker datePickerPartidos;

    private Button btnAnhadir;

    boolean initialSelection = true;
    boolean invalidFields;

    DeportesService deportesService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match);

        deportesService = DeportesService.retrofit.create(DeportesService.class);

        datePickerPartidos = (DatePicker) findViewById(R.id.datePickerPartidos);

        spinnerEvento = (Spinner)findViewById(R.id.spinnerEvento);
        final ArrayAdapter<String> spinnerEventoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerEvento.setAdapter(spinnerEventoAdapter);

        spinnerEquipo1 = (Spinner)findViewById(R.id.spinnerEquipo1);
        final ArrayAdapter<String> spinnerEquipo1Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerEquipo1.setAdapter(spinnerEquipo1Adapter);

        spinnerEquipo2 = (Spinner)findViewById(R.id.spinnerEquipo2);
        final ArrayAdapter<String> spinnerEquipo2Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        //spinnerEventoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEquipo2.setAdapter(spinnerEquipo2Adapter);

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

        /*
        spinnerEventoAdapter.add("Evento1");
        spinnerEventoAdapter.add("MiEvento123");
        spinnerEventoAdapter.notifyDataSetChanged();
        */
        spinnerEvento.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!initialSelection)
                {
                    //Query al servidor con el evento
                    Toast.makeText(AddMatchActivity.this, spinnerEvento.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

                    //Obtiene el codigo del evento
                    int idEvento = loadEventosRegistrados(spinnerEvento.getSelectedItem().toString());

                    Call<List<Equipo>> callEquipos = deportesService.getEquiposEnEvento(idEvento);

                    callEquipos.enqueue(new Callback<List<Equipo>>() {
                        @Override
                        public void onResponse(Call<List<Equipo>> call, Response<List<Equipo>> response) {
                                for(Equipo equipo : response.body())
                                {
                                    spinnerEquipo1Adapter.add(equipo.toString());
                                    spinnerEquipo2Adapter.add(equipo.toString());
                                }

                            spinnerEquipo1Adapter.notifyDataSetChanged();
                            spinnerEquipo2Adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<List<Equipo>> call, Throwable t) {
                            Toast.makeText(AddMatchActivity.this, "Fallo al obtener los equipos. Por favor intente nuevamente" /*+ t.getMessage()*/, Toast.LENGTH_LONG).show();
                        }
                    });

                }
                else
                {
                    //El metodo se llama cuando se construye la vista, añadimos un flag
                    //para diferenciar cuando el evento se dispara por una accion del usuario
                    initialSelection = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnAnhadir = (Button) findViewById(R.id.btnAnhadirPartido);

        btnAnhadir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                invalidFields = false;

                //Verifica que la fecha no sea pasada
                String fecha = datePickerPartidos.getDayOfMonth() + "/" + (datePickerPartidos.getMonth()+1) + "/" + datePickerPartidos.getYear();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                Date strDate;

                try
                {
                    strDate = sdf.parse(fecha);
                    if (new Date().after(strDate))
                    {
                        Toast.makeText(AddMatchActivity.this, "La fecha no es válida!", Toast.LENGTH_LONG).show();
                        invalidFields = true;
                    }
                } catch (ParseException e)
                {
                    e.printStackTrace();
                }

                //Selecciono el mismo equipo en ambos spinners
                if(spinnerEquipo1.getSelectedItem().equals(spinnerEquipo2.getSelectedItem()))
                {
                    Toast.makeText(AddMatchActivity.this, "No puede seleccionar el mismo equipo en ambos campos!", Toast.LENGTH_SHORT).show();
                    invalidFields = true;
                }


                //Todos los datos son correctos
                if(!invalidFields)
                {
                    Partido partido = new Partido();
                    partido.setEquipo1(spinnerEquipo1.getSelectedItem().toString());
                    partido.setEquipo2(spinnerEquipo2.getSelectedItem().toString());
                    fecha =  datePickerPartidos.getYear()+ "/" + (datePickerPartidos.getMonth()+1) + "/" +datePickerPartidos.getDayOfMonth();
                    partido.setFecha(fecha);
                    partido.setHoraInicio(spinnerHorario.getSelectedItem().toString());


                    Call<ResponseBody> callPostPartido = deportesService.postPartido(partido);

                    callPostPartido.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
                        {
                            //El servidor responde un OK a la insercion
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(AddMatchActivity.this, "Por favor intente nuevamente", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });
    }

    public int loadEventosRegistrados(String nombreEvento)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("eventosRegistrados", Context.MODE_PRIVATE);
        int idEvento = sharedPreferences.getInt(nombreEvento, -1);

        if(idEvento > 0)
        {
           return idEvento;
        }
        else
        {
            //Como deberia manejarse el error?
            //Existe la posibilidad de que exista tal error?
            return -1;
        }
    }



}
