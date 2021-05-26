package com.example.plantilla.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.plantilla.R;
import com.example.plantilla.modelo.Propietario;
import com.example.plantilla.request.ApiClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MenuNavegableActivity extends AppCompatActivity
{

    private AppBarConfiguration mAppBarConfiguration;
    private TextView tvNombreUsuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_navegable);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_exit_2);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder alertDialogBuilder=new AlertDialog. Builder (MenuNavegableActivity.this);
                alertDialogBuilder.setTitle("Confirmar Salir..!!");
                alertDialogBuilder.setIcon (R.drawable.ic_exit);
                alertDialogBuilder.setMessage("Estas seguro que quieres salir de la mejor app de la historia");
                alertDialogBuilder.setCancelable(false);

                alertDialogBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                        System.exit(0);

                    }
                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText (getApplicationContext(), "Sabia eleccion", Toast.LENGTH_LONG).show();
                    }
                });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();


            } //
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_perfil, R.id.nav_ubicacion, R.id.inmueblesFragment, R.id.inquilinosFragment, R.id.contratosVigentesFragment)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View root = navigationView.getHeaderView(0);
        //TextView nombre = root.findViewById(R.id.textView);
        //nombre.setText(obtenerNombreUsuarioActual());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_navegable, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    private String obtenerNombreUsuarioActual() {
        ApiClient apiClient = ApiClient.getApi();
        Propietario propietario = apiClient.obtenerUsuarioActual();
        return propietario.getApellido() + " " + propietario.getNombre() + " - " + propietario.getEmail();
    }

}