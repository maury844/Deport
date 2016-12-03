package projects.brainiacs.formtest.Activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import projects.brainiacs.formtest.R;

public class AdminMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private Button btnEventos;
    private Button btnFixture;

    private ImageView imgsNoticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //------------------------------------------------------------------------------------------

        btnEventos =(Button)findViewById(R.id.btnEventos);
        btnFixture =(Button)findViewById(R.id.btnPartidos);
        imgsNoticias = (ImageView) findViewById(R.id.imgViewNoticias);


        btnEventos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                //PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                Intent intent = new Intent(getApplicationContext(), EventActivity.class);
                startActivity(intent);

                Notification notificacion = new NotificationCompat.Builder(AdminMainActivity.this)
                        .setContentTitle("Deportes UPB")
                        .setContentText("Tienes un partido hoy!")
                        .setSmallIcon(R.drawable.chico)
                        // .setContentIntent(pIntent)
                        .setDefaults(Notification.DEFAULT_SOUND)
                        .build();

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0, notificacion);


                //Intent intent = new Intent(getApplicationContext(), AddTeamActivity.class);
                //startActivity(intent);
            }
        });

        btnFixture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), MatchActivity.class);
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
/*
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_jugador) {
            // Handle the camera event
            Intent intent = new Intent(getApplicationContext(), AddTeamMemberActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_equipo) {
            Intent intent = new Intent(getApplicationContext(), AddTeamActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_evento) {
            Intent intent = new Intent(getApplicationContext(), AddEventActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_partido) {
            Intent intent = new Intent(getApplicationContext(), AddMatchActivity.class);
            startActivity(intent);
        } /*else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /************************************************************************************************************************************
     *  Metodo para salir de la aplicacion con doubleBackPress
     ************************************************************************************************************************************/

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce)
        {
            //Mostrar una alerta
            new AlertDialog.Builder(AdminMainActivity.this)
                    .setTitle("Salir")
                    .setMessage("¿Esta seguro que desea salir de la aplicación?")
                    .setPositiveButton(/*android.R.string.yes*/"Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //Salir de la app
                            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                            homeIntent.addCategory( Intent.CATEGORY_HOME );
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
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }




}
