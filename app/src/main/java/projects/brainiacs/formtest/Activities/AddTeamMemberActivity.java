package projects.brainiacs.formtest.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import projects.brainiacs.formtest.DeportesService;
import projects.brainiacs.formtest.Models.Deportista;
import projects.brainiacs.formtest.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTeamMemberActivity extends AppCompatActivity /*implements Callback<Player> */{

    private ImageButton btnBuscar;
    private EditText txtCodigo;
    private TextView txtNombres, txtApellidos;
    private Button btnAnhadir;
    DeportesService deportesService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team_member);

        btnBuscar = (ImageButton) findViewById(R.id.btnBuscar);
        txtCodigo = (EditText) findViewById(R.id.txtCodigo);
        txtNombres = (TextView) findViewById(R.id.txtNombres);
        txtApellidos = (TextView) findViewById(R.id.txtApellidos);
        btnAnhadir = (Button) findViewById(R.id.btnAnhadir);
        deportesService = DeportesService.retrofit.create(DeportesService.class);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                //Oculta el teclado luego de presionarse el boton
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                
                if(txtCodigo.getText().length() > 0)
                {
                    Call<Deportista> call = deportesService.getDeportista(txtCodigo.getText().toString());

                    call.enqueue(new Callback<Deportista>() {
                        @Override
                        public void onResponse(Call<Deportista> call, Response<Deportista> response) {
                            //Toast.makeText(AddTeamMemberActivity.this, "SUCCESS!! :D", Toast.LENGTH_LONG).show();
                            txtNombres.setText(response.body().getNombre());
                            txtApellidos.setText(response.body().getApellidoPaterno());
                        }

                        @Override
                        public void onFailure(Call<Deportista> call, Throwable t) {
                            Toast.makeText(AddTeamMemberActivity.this, "Por favor intente nuevamente" /*+ t.getMessage()*/, Toast.LENGTH_LONG).show();
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

                txtNombres.setText("");
                txtApellidos.setText("");
                Toast.makeText(AddTeamMemberActivity.this, "Miembro añadido", Toast.LENGTH_SHORT).show();
            }
        });

    }

/*
    @Override
    public void onResponse(Call<Player> call, Response<Player> response) {
        if (response.isSuccessful())
        {
            Player player = response.body();
            StringBuilder builder = new StringBuilder();
            /*
            for (Player player: players)
            {
                builder.append(player.getNombre() + " " + player.toString());
            }

            builder.append(player.toString());
            Toast.makeText(AddTeamMemberActivity.this, builder.toString(), Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(AddTeamMemberActivity.this, "Error code " + response.code(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<Player> call, Throwable t) {
        Toast.makeText(AddTeamMemberActivity.this, "Did not work " +  t.getMessage(), Toast.LENGTH_SHORT).show();
    }
    */
}
