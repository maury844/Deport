package projects.brainiacs.formtest.Activities;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import projects.brainiacs.formtest.DeportesService;
import projects.brainiacs.formtest.Models.Deportista;
import projects.brainiacs.formtest.Models.Equipo;
import projects.brainiacs.formtest.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTeamMemberActivity extends AppCompatActivity /*implements Callback<Player> */{

    private ImageButton btnBuscar;
    private EditText txtCodigo;
    private TextView txtNombres, txtApellidos;
    private Button btnAnhadir;
    private Spinner spinnerEquipos;
    DeportesService deportesService;

    boolean loadedTeams = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team_member);
        setTitle("Universidad Privada Boliviana");

        btnBuscar = (ImageButton) findViewById(R.id.btnBuscar);
        txtCodigo = (EditText) findViewById(R.id.txtCodigo);
        txtNombres = (TextView) findViewById(R.id.txtNombres);
        txtApellidos = (TextView) findViewById(R.id.txtApellidos);
        btnAnhadir = (Button) findViewById(R.id.btnAnhadir);

        deportesService = DeportesService.retrofit.create(DeportesService.class);

        spinnerEquipos = (Spinner) findViewById(R.id.spinnerEquipos);
        final ArrayAdapter<String> spinnerEquiposAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerEquipos.setAdapter(spinnerEquiposAdapter);

        //Carga los equipos "actuales" al spinner
        final Call<List<Equipo>> callEquipos  = deportesService.getEquiposActuales();

        callEquipos.enqueue(new Callback<List<Equipo>>() {
            @Override
            public void onResponse(Call<List<Equipo>> call, Response<List<Equipo>> response) {
                for(Equipo equipo : response.body())
                {
                    spinnerEquiposAdapter.add(equipo.toString());
                }

                spinnerEquiposAdapter.notifyDataSetChanged();

                loadedTeams = true;
            }

            @Override
            public void onFailure(Call<List<Equipo>> call, Throwable t) {
                Toast.makeText(AddTeamMemberActivity.this, "Fallo al obtener los equipos. Por favor intente nuevamente" /*+ t.getMessage()*/, Toast.LENGTH_LONG).show();

                //Reintentar
                //callEquipos.clone();

            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                //Oculta el teclado luego de presionarse el boton
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                
                if(txtCodigo.getText().toString().trim().length() > 0)
                {
                    Call<Deportista> call = deportesService.getDeportista(txtCodigo.getText().toString());

                    call.enqueue(new Callback<Deportista>() {
                        @Override
                        public void onResponse(Call<Deportista> call, Response<Deportista> response) {
                            //Toast.makeText(AddTeamMemberActivity.this, "SUCCESS!! :D", Toast.LENGTH_LONG).show();
                            txtNombres.setText(response.body().getNombre());
                            txtApellidos.setText(response.body().getApellidoPaterno() + " " + response.body().getApellidoMaterno());
                        }

                        @Override
                        public void onFailure(Call<Deportista> call, Throwable t) {
                            Toast.makeText(AddTeamMemberActivity.this, "No se pudo obtener el registro. Por favor intente nuevamente" /*+ t.getMessage()*/, Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(AddTeamMemberActivity.this, "Introduzca un codigo por favor!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnAnhadir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                Deportista deportista = new Deportista();
                deportista.setCodigo(Integer.parseInt(txtCodigo.getText().toString()));
                deportista.setNombre(txtNombres.getText().toString());
                deportista.setApellidoPaterno(txtApellidos.getText().toString());
                deportista.setNombreEquipo(spinnerEquipos.getSelectedItem().toString());

                Call<ResponseBody> callPostPartido = deportesService.postDeportista(deportista);

                callPostPartido.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
                    {
                        //El servidor responde un OK a la insercion
                        Toast.makeText(AddTeamMemberActivity.this, "Miembro añadido con éxito!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(AddTeamMemberActivity.this, "Por favor intente nuevamente", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }


}
