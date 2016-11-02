package projects.brainiacs.formtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;


import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class AddTeamActivity extends AppCompatActivity {

    private ImageButton btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);

        btnSearch = (ImageButton) findViewById(R.id.btnSearch);
/*
        btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                HttpClient httpclient = new DefaultHttpClient();
                HttpGet httpget= new HttpGet(URL);

                HttpResponse response = httpclient.execute(httpget);

                if(response.getStatusLine().getStatusCode()==200){
                    String server_response = EntityUtils.toString(response.getEntity());
                    Log.i("Server response", server_response );
                } else {
                    Log.i("Server response", "Failed to get server response" );
                }
            }
        });
        */


    }



}
