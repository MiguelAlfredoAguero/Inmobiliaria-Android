package com.example.plantilla.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.plantilla.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener
{
    private EditText etUsuario, etContrasena;
    private Button btnLogin;
    private MainActivityViewModel mainActivityViewModel;
    private SensorManager sensorManager;
    private List<Sensor> sensores;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pedirPermisos();

        mainActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        inicializarComponentes();
        mainActivityViewModel.getMensajeMutable().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String mensaje) {
                //Dialogo

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Información")
                        .setMessage(mensaje)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

            }
        });

        mainActivityViewModel.getLoginMutable().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean logueado) {
                    Intent i = new Intent(getApplicationContext(), MenuNavegableActivity.class);
                    startActivity(i);
            }
        });

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(sensores.size() > 0){
            sensorManager.registerListener(this, sensores.get(0), SensorManager.SENSOR_DELAY_GAME);
        }
        flag = true;
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    private void pedirPermisos(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1000);
        }

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.INTERNET},1000);
        }


        pedirPermisosMapas();
    }

    private void pedirPermisosMapas(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},1000);
    }
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1000);
}}


    private void inicializarComponentes() {
        etUsuario = findViewById(R.id.etUsuario);
        etContrasena = findViewById(R.id.etContrasena);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = etUsuario.getText().toString();
                String contrasena = etContrasena.getText().toString();

                mainActivityViewModel.verificarDatos(usuario, contrasena);

            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        float x = event.values[0];
        //Log.d("msj", x + "");

        if(flag && (x > 10 || x < -10)){
            Intent i = new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel: 555"));
            startActivity(i);
            flag = false;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }
}