package com.example.plantilla.ui.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInmuebleViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> detalleInmuebleMutable;
    private Context context;

    public DetalleInmuebleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Inmueble> getDetalleInmuebleMutable() {
        if ( detalleInmuebleMutable == null ) {
            detalleInmuebleMutable = new MutableLiveData<>();
        }
        return detalleInmuebleMutable;
    }

    public void cargarDetalleInmueble(int id) {

        Call<Inmueble> detalleInmueble = ApiClient.getMyApiClient().detalleInmueble(id, ApiClient.obtenerToken(context));
        detalleInmueble.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()) {
                    detalleInmuebleMutable.postValue(response.body());
                } else {
                    Toast.makeText(context, "Inmuelbe no econtrado", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(context, "Algo ocurrio", Toast.LENGTH_LONG).show();
            }
        });


    }
    public void actualizarDetalleInmueble( Inmueble inmueble ) {

        Call<Inmueble> editInmueble = ApiClient.getMyApiClient().editInmueble(inmueble, ApiClient.obtenerToken(context));

        Log.d("msj", "Inmueble id  " + inmueble.getid());
        Log.d("msj", "Inmueble direcion  " + inmueble.getDireccion());
        Log.d("msj", "Inmueble uso  " + inmueble.getUsoInmueble());
        Log.d("msj", "Inmueble tipo  " + inmueble.getTipoInmueble());
        Log.d("msj", "Inmueble ambientes  " + inmueble.getAmbientes());
        Log.d("msj", "Inmueble precio  " + inmueble.getPrecio());
        //Log.d("msj", "Inmueble propietario  " + inmueble.getPropietario().getEmail());
        Log.d("msj", "Inmueble estado  " + inmueble.isEstado());
        Log.d("msj", "Inmueble avatar  " + inmueble.getavatar());

        editInmueble.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if ( response.isSuccessful() ) {
                    detalleInmuebleMutable.postValue(response.body());
                } else {
                    Log.d("msj", "Inmueble no encontrado." + response.message());
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(context, "Ocurrio algún error.", Toast.LENGTH_LONG).show();
            }
        });

        /*
        ApiClient apiClient = ApiClient.getApi();
        apiClient.actualizarInmueble(inmueble);
        detalleInmuebleMutable.setValue(inmueble);
        */
    }
}
