package projects.brainiacs.formtest.Activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import projects.brainiacs.formtest.R;

public class UserMainActivity extends AppCompatActivity {

    private Button btnEventos;
    private Button btnFixture;

    private ImageView imgsNoticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        setTitle("Usuario");

        btnEventos = (Button) findViewById(R.id.btnEventos);
        btnFixture = (Button) findViewById(R.id.btnPartidos);
        imgsNoticias = (ImageView) findViewById(R.id.imgViewNoticias);

        btnEventos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(UserMainActivity.this, EventActivity.class);
                startActivity(intent);

                Notification notificacion = new NotificationCompat.Builder(UserMainActivity.this)
                        .setContentTitle("Deportes UPB")
                        .setContentText("Tienes un partido hoy!")
                        .setSmallIcon(R.drawable.chico)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .build();

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0, notificacion);



            }
        });

        btnFixture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MatchActivity.class);
                intent.putExtra("permisosAdministrador", false);
                startActivity(intent);
            }
        });


        //Animacion en el image view
        AnimationDrawable animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.final_challenge), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.campeonato_interuniversitario), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.bailando_upb), 3000);
        animation.setOneShot(false);

        imgsNoticias.setImageDrawable(animation);

        // start the animation!
        animation.start();

    }

    /************************************************************************************************************************************
     * Metodo para salir de la aplicacion con doubleBackPress
     ************************************************************************************************************************************/

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            //Mostrar una alerta
            new AlertDialog.Builder(UserMainActivity.this)
                    .setTitle("Salir")
                    .setMessage("¿Esta seguro que desea salir de la aplicación?")
                    .setPositiveButton(/*android.R.string.yes*/"Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //Salir de la app
                            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                            homeIntent.addCategory(Intent.CATEGORY_HOME);
                            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(homeIntent);
                        }
                    })
                    .setNegativeButton(/*android.R.string.no*/"No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //No hacer nada
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Presione atrás otra vez si desea salir", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

}



