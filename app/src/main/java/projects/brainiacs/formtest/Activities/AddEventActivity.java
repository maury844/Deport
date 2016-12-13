package projects.brainiacs.formtest.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import projects.brainiacs.formtest.DeportesService;
import projects.brainiacs.formtest.Models.Evento;
import projects.brainiacs.formtest.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEventActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Button btnCrearEvento;
    private EditText txtNombre, txtDescripcion, txtLugar;
    private Spinner spinnerDeportes;

    boolean invalidFields = false;

    DeportesService deportesService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        setTitle("Universidad Privada Boliviana");


        final ArrayList<EditText> listaTxt = new ArrayList<>();

        datePicker = (DatePicker) findViewById(R.id.datePickerEventos);
        txtNombre = (EditText) findViewById(R.id.txtNombreEvento);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);
        txtLugar = (EditText) findViewById(R.id.txtLugar);
        btnCrearEvento = (Button) findViewById(R.id.btnCrearEvento);
        spinnerDeportes = (Spinner) findViewById(R.id.spinnerEventos);
        final ArrayAdapter<String> spinnerDeportesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerDeportes.setAdapter(spinnerDeportesAdapter);


        deportesService = DeportesService.retrofit.create(DeportesService.class);

        //Añadimos los txt a la lista para realizar la verificacion de manera mas sencilla
        listaTxt.add(txtNombre);
        listaTxt.add(txtDescripcion);
        listaTxt.add(txtLugar);


        //Cargar los eventos al spinner
        Call<List<String>> callDisciplinas = deportesService.getDisciplinas();

        callDisciplinas.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                for (String disciplina : response.body())
                {
                    spinnerDeportesAdapter.add(disciplina);
                }
                spinnerDeportesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(AddEventActivity.this, "No se pudo obtener las disciplinas.\n Por favor intente nuevamente" /*+ t.getMessage()*/, Toast.LENGTH_LONG).show();
            }
        });


        btnCrearEvento.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                //Ocultar el teclado cuando se haga click en el boton
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

/*
                for(EditText et : listaTxt)
                {
                    if(et.hasFocus())
                    {
                        et.clearFocus();
                    }
                }
*/
                //Cada que se clickee reseteamos el valor a falso
                invalidFields = false;

                //Verifica que todos los campos esten llenos y sean correctos
                for(EditText et : listaTxt)
                {
                    if(isEmpty(et))
                    {
                        et.setError("Campos Requeridos");
                        invalidFields = true;
                    }
                    else if( !isValid(et) )
                    {
                        et.setError("El nombre no puede contener caracteres especiales");
                        invalidFields = true;
                    }
                }

                //Verifica que la fecha no sea pasada
                String fechaInicio = datePicker.getDayOfMonth() + "/" + (datePicker.getMonth()+1) + "/" + datePicker.getYear();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                Date strDate;

                try
                {
                    strDate = sdf.parse(fechaInicio);
                    if (new Date().after(strDate))
                    {
                        Toast.makeText(AddEventActivity.this, "La fecha no es válida!", Toast.LENGTH_LONG).show();
                        invalidFields = true;
                    }
                } catch (ParseException e)
                {
                    e.printStackTrace();
                }


                if(!invalidFields)
                {
                    //Creamos el Evento
                    Evento evento = new Evento();
                    evento.setNombre(txtNombre.getText().toString());
                    evento.setDescripcion(txtDescripcion.getText().toString());
                    evento.setLugar(txtLugar.getText().toString());

                    //evento.setFechaInicio(new Date(datePicker.getDayOfMonth(), datePicker.getMonth(), datePicker.getYear()));
                    //evento.setFechaInicio(new Date(datePicker.get));
                    evento.setFechaInicio(fechaInicio);

/*
                    Call<ResponseBody> call = deportesService.postEvento(evento);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            //Toast.makeText(AddTeamMemberActivity.this, "SUCCESS!! :D", Toast.LENGTH_LONG).show();
                            //txtApellidos.setText(response.body().getApellidoPaterno());

                            //Aqui acceder al body y anhadir a los shared preferences el key value pair de NombreEvento y idEvento;
                            //response.body().;
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(AddEventActivity.this, "Por favor intente nuevamente", Toast.LENGTH_LONG).show();
                        }
                    });
                }
*/

                    Call<Evento> call = deportesService.postEvento(evento);

                    call.enqueue(new Callback<Evento>() {
                        @Override
                        public void onResponse(Call<Evento> call, Response<Evento> response) {

                            //Aqui acceder al body y anhadir a los shared preferences el key value pair de NombreEvento y idEvento;
                            int codigo = response.body().getId();
                            String nombre = response.body().getNombre();
                            savePreferences(nombre, codigo);
                        }

                        @Override
                        public void onFailure(Call<Evento> call, Throwable t) {
                            Toast.makeText(AddEventActivity.this, "Por favor intente nuevamente", Toast.LENGTH_LONG).show();
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
       // return w.matches("[A-Za-z][^.]*");
        //Al menos una letra y luego una combinacion cualquiera de letras y numeros
        return w.matches("[a-zA-Z]([a-zA-Z_0-9 ])*");
    }

    //Save in Shared Preferences the key and value
    private void savePreferences(String key, int value)
    {
        //SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences sharedPreferences = getSharedPreferences("eventosRegistrados", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }
}
