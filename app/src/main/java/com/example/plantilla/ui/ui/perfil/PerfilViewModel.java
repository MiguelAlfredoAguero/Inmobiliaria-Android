package com.example.plantilla.ui.ui.perfil;

import android.app.Application;
import android.content.Context;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Propietario;
import com.example.plantilla.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel
{
    private MutableLiveData<Propietario> propietarioMutable;
    private MutableLiveData<String> botonEditarMutable;
    private MutableLiveData<String> botonGuardarMutable;
    private Context context;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Propietario> getPropietarioMutable () {
        if ( propietarioMutable == null ) {
            propietarioMutable = new MutableLiveData<>();
        }
        return propietarioMutable;
    }
    public LiveData<String> getBotonEditarMutable () {
        if ( botonEditarMutable == null ) {
            botonEditarMutable = new MutableLiveData<>();
        }
        return botonEditarMutable;
    }
    public LiveData<String> getBotonGuardarMutable () {
        if ( botonGuardarMutable == null ) {
            botonGuardarMutable = new MutableLiveData<>();
        }
        return botonGuardarMutable;
    }


    public void cargarPerfil() {

        Call<Propietario> propietarioActual = ApiClient.getMyApiClient().propietarioActual(ApiClient.obtenerToken(context));
        propietarioActual.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if ( response.isSuccessful() ) {
                    propietarioMutable.postValue(response.body());
                } else {
                    Toast.makeText(context, "No encontrado", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(context, "cargarPerfil(): Ocurrio un error", Toast.LENGTH_LONG).show();
            }
        });


        /*
        ApiClient api = ApiClient.getApi();
        Propietario propietario = api.obtenerUsuarioActual();
        propietarioMutable.setValue(propietario);*/
    }

    public void guardarPerfil(Propietario propietario) {

        Call<Propietario> editPerfil = ApiClient.getMyApiClient().editPerfil(propietario, ApiClient.obtenerToken(context) );
        editPerfil.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if ( response.isSuccessful() ) {
                    propietarioMutable.postValue(response.body());
                } else {
                    Log.d("msj","No encontrado " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Log.d("msj","guardarPerfil(): Ocurrio un error");
            }
        });

        /*
        ApiClient ap = ApiClient.getApi();
        ap.actualizarPerfil(p);
        propietarioMutable.setValue(p);
        */
    }

    public void HabDes(String textoBoton, Propietario p) {
        if ( textoBoton == "Editar" ) {
            botonEditarMutable.setValue(textoBoton);
        } else if (textoBoton == "Guardar") {
            guardarPerfil(p);
            botonGuardarMutable.setValue(textoBoton);
        }
    }

}