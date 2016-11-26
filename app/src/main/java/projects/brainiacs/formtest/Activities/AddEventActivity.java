package projects.brainiacs.formtest.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
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



        final ArrayList<EditText> listaTxt = new ArrayList<>();

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        txtNombre = (EditText) findViewById(R.id.txtNombreEvento);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);
        txtLugar = (EditText) findViewById(R.id.txtLugar);
        btnCrearEvento = (Button) findViewById(R.id.btnCrearEvento);
        spinnerDeportes = (Spinner) findViewById(R.id.spinnerDeportes);

        deportesService = DeportesService.retrofit.create(DeportesService.class);


        listaTxt.add(txtNombre);
        listaTxt.add(txtDescripcion);
        listaTxt.add(txtLugar);

        //Cargando el spinner con deportes
        //Resources res = getResources();
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, res.getStringArray(R.array.deportes) );
        //spinnerDeportes.setAdapter(adapter);



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
                /*fechaInicio = Integer.toString(datePicker.getDayOfMonth());
                fechaInicio += "/";
                fechaInicio += Integer.toString((1+datePicker.getMonth()));
                fechaInicio += "/";
*/
                //Toast.makeText(AddEventActivity.this, "Fecha: " + fechaInicio, Toast.LENGTH_LONG).show();
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


                    Call<ResponseBody> call = deportesService.postEvento(evento);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            //Toast.makeText(AddTeamMemberActivity.this, "SUCCESS!! :D", Toast.LENGTH_LONG).show();
                            //txtApellidos.setText(response.body().getApellidoPaterno());
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
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
        return w.matches("[a-zA-Z][a-zA-Z_0-9]*");
    }
}
