package projects.brainiacs.formtest.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.ResponseBody;
import projects.brainiacs.formtest.DeportesService;
import projects.brainiacs.formtest.Models.Partido;
import projects.brainiacs.formtest.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddResultActivity extends AppCompatActivity {

    private EditText editTxtPuntaje1, editTxtPuntaje2;
    private TextView txtViewEquipo1,txtViewEquipo2, txtViewId;
    private Button btnGuardarResultado;

    DeportesService deportesService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_result);

        Intent intent = getIntent();
        final String idPartido = intent.getExtras().getString("idPartido");
        final String equipo1 = intent.getExtras().getString("equipo1");
        final String equipo2 = intent.getExtras().getString("equipo2");

        deportesService = DeportesService.retrofit.create(DeportesService.class);

        editTxtPuntaje1 = (EditText) findViewById(R.id.editTxtEquipo1);
        editTxtPuntaje2 = (EditText) findViewById(R.id.editTxtEquipo2);

        txtViewEquipo1 = (TextView) findViewById(R.id.textViewEquipo1);
        txtViewEquipo2 = (TextView) findViewById(R.id.textViewEquipo2);
        txtViewId = (TextView) findViewById(R.id.txtIdPartido);

        txtViewEquipo1.setText(equipo1);
        txtViewEquipo2.setText(equipo2);
        txtViewId.setText(idPartido);

        btnGuardarResultado = (Button) findViewById(R.id.btnGuardarResultado);


        btnGuardarResultado.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String puntaje1 = editTxtPuntaje1.getText().toString();
                String puntaje2 = editTxtPuntaje2.getText().toString();
                String puntaje = puntaje1 + " - " + puntaje2;

                Partido partido = new Partido();
                partido.setIdPartido(idPartido);
                partido.setPuntaje(puntaje);

                partido.setEquipo1(equipo1);
                partido.setEquipo2(equipo2);

                Call<ResponseBody> callPostResultado = deportesService.postResultado(partido);

                callPostResultado.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        //El servidor responde un OK a la insercion
                        Toast.makeText(AddResultActivity.this, "Resultado guardado con éxito!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(AddResultActivity.this, "Por favor intente nuevamente", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}
