package projects.brainiacs.formtest;

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

public class AddTeamMemberActivity extends AppCompatActivity {

    private ImageButton btnSearch;
    private EditText txtCode;
    private TextView txtFirstName, txtLastName;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team_member);

        btnSearch = (ImageButton) findViewById(R.id.btnSearch);
        txtCode = (EditText) findViewById(R.id.txtCode);
        txtFirstName = (TextView) findViewById(R.id.txtFirstName);
        txtLastName = (TextView) findViewById(R.id.txtLastName);
        addButton = (Button) findViewById(R.id.btnAdd);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                //Hides the keyboard when the button is pressed
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);



                //Query to Server here
                if(txtCode.getText().toString().equals("28469"))
                {
                    txtFirstName.setText("Mauricio Antonio");
                    txtLastName.setText("Fernandez Rosazza");
                }
                else
                {
                    //txtFirstName.setTextSize(10.0f);
                    txtFirstName.setText("No existe registro con ese código");
                    //txtFirstName.setTextSize(22);
                    txtLastName.setText("Intente nuevamente.");
                }
            }
        });


        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                txtFirstName.setText("");
                txtLastName.setText("");
                Toast.makeText(AddTeamMemberActivity.this, "Miembro añadido", Toast.LENGTH_SHORT).show();
            }
        });


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
