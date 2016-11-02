package projects.brainiacs.formtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserMainActivity extends AppCompatActivity {

    private Button btnMatches;
    private Button btnFixture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        setTitle("Universidad Privada Boliviana");

        btnMatches =(Button)findViewById(R.id.btnMatches);
        btnFixture =(Button)findViewById(R.id.button2);

        btnMatches.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                //PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                Notification notificacion = new NotificationCompat.Builder(UserMainActivity.this)
                        .setContentTitle("Deportes UPB")
                        .setContentText("Tienes un partido hoy!")
                        .setSmallIcon(R.drawable.chico)
                        // .setContentIntent(pIntent)
                        .setDefaults(Notification.DEFAULT_SOUND)
                        .build();

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0, notificacion);
            }
        });

        btnFixture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), MatchesActivity.class);
                startActivity(intent);
            }
        });

    }




}
