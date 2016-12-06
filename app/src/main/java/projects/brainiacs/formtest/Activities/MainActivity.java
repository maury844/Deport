package projects.brainiacs.formtest.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import projects.brainiacs.formtest.R;

public class MainActivity extends AppCompatActivity {

    private EditText txtCodigo;
    private EditText txtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        setTitle("Universidad Privada Boliviana");

        txtCodigo = (EditText) findViewById(R.id.txtCodigo);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setFocusable(false);

        int codigoConocido = loadPrefConocido();
        //Si existe guardado ya el valor dle password (EN EL CELULAR), hace login directamente
        if(codigoConocido != -1)
        {
            Toast.makeText(this, "Logeando con shared preferences", Toast.LENGTH_SHORT).show();

            if(!loadLoginValues(codigoConocido).equals(""))
            {
                // es admin
                if(codigoConocido == 1)
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

       if(isOnline())
        {
            //Toast.makeText(this, "TENGO INTERNET", Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(this, "Por favor, conéctese a internet!", Toast.LENGTH_LONG).show();
        }

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

        //EditText txtCodigo = (EditText) findViewById(R.id.txtCodigo);
//        EditText txtPassword = (EditText) findViewById(R.id.txtPassword);
        //EditText txtAge = (EditText) findViewById(R.id.txtAge);



        int codigo = Integer.parseInt(txtCodigo.getText().toString());
        String password = txtPassword.getText().toString();

        Toast.makeText(this, "Bienvenido\n" + txtCodigo.getText().toString() + "\n"
                + txtPassword.getText().toString() + "\n", Toast.LENGTH_LONG).show();

        txtCodigo.setText("");
        txtPassword.setText("");

        //Hardcoded Admin USER
        if( codigo == 1 && password.equals("upb"))
        {
            Intent intent = new Intent(MainActivity.this, AdminMainActivity.class);
            MainActivity.this.startActivity(intent);
            savePreferencesConocido("conocido", codigo);
            savePreferencesLogin(codigo, password);
        }
        else
        {
            Intent intent = new Intent(MainActivity.this, UserMainActivity.class);
            MainActivity.this.startActivity(intent);
            savePreferencesConocido("conocido", codigo);
            savePreferencesLogin(codigo, password);
        }
    }

    private void savePreferencesLogin(int key, String value)
    {
        //SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences sharedPreferences = getSharedPreferences("loginValues", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Integer.toString(key), value);
        editor.commit();
    }

    private void savePreferencesConocido(String key, int value)
    {
        //SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences sharedPreferences = getSharedPreferences("loginValues", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int loadPrefConocido()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("loginValues", Context.MODE_PRIVATE);
        int codigoConocido = sharedPreferences.getInt("conocido", -1);

        //retorna -1 si nunca existio un login, retorna el codigo si ya existio alguna vez
        return codigoConocido;
    }

    public String loadLoginValues(int codigo)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("loginValues", Context.MODE_PRIVATE);
        String password = sharedPreferences.getString(Integer.toString(codigo), "");

        if(!password.equals(""))
        {
            return password;
        }
        else
        {
            //Como deberia manejarse el error?
            //Existe la posibilidad de que exista tal error?
            return "";
        }
    }


}
