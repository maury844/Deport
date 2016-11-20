package projects.brainiacs.formtest;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtCode;
    private EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        setTitle("Universidad Privada Boliviana");

        txtCode = (EditText) findViewById(R.id.txtCode);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

       if(isOnline())
        {
            Toast.makeText(this, "TENGO INTERNET", Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(this, "No TENGO INTERNET :V", Toast.LENGTH_LONG).show();
        }

/*
        txtCode.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (!event.isShiftPressed()) {
                                // the user is done typing.
                                Toast.makeText(MainActivity.this, "Finished Typing Code", Toast.LENGTH_SHORT).show();
                                return true; // consume.
                            }
                        }
                        return false; // pass on to other listeners.
                    }
                });
*/
    }

    protected boolean isOnline()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnectedOrConnecting()){
            return true;
        } else {
            return false;
        }
    }

    public void save(View v)
    {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        //EditText txtCode = (EditText) findViewById(R.id.txtCodigo);
//        EditText txtPassword = (EditText) findViewById(R.id.txtPassword);
        //EditText txtAge = (EditText) findViewById(R.id.txtAge);



        /*
        s+="{\"student\":{";
		s+="\"firstName\":\"" + nombre   + "\",";
		s+="\"lastName\":\""  + apellido + "\",";
		s+="\"career\":\""    + carrera  + "\",";
		s+="\"code\":"        + codigo   + ",";
		s+="\"semester\":"    + semestre + "}}";
        */

        String code = txtCode.getText().toString();
        String password = txtPassword.getText().toString();

        Toast.makeText(this, "Bienvenido\n" + txtCode.getText().toString() + "\n"
                + txtPassword.getText().toString() + "\n"/*
                + txtAge.getText().toString() + "\n"*/, Toast.LENGTH_LONG).show();

        txtCode.setText("");
        txtPassword.setText("");

        //Hardcoded Admin USER
        if( code.equals("1") && password.equals("upb"))
        {
            Intent intent = new Intent(MainActivity.this, AdminMainActivity.class);
            MainActivity.this.startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(MainActivity.this, UserMainActivity.class);
            MainActivity.this.startActivity(intent);
        }
    }

}
