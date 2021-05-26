package com.example.plantilla.ui.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.modelo.Propietario;
import com.example.plantilla.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesViewModel extends AndroidViewModel {

    private MutableLiveData<List<Inmueble>> listaInmuebleMutable;
    private Context context;

    public InmueblesViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<List<Inmueble>> getListaInmuebleMutable () {
        if ( listaInmuebleMutable == null ) {
            listaInmuebleMutable = new MutableLiveData<>();
        }
        return listaInmuebleMutable;
    }

    public void cargarInmuebles() {

        Call<List<Inmueble>> listaInmuebles = ApiClient.getMyApiClient().listaInmuebles(ApiClient.obtenerToken(context));
        listaInmuebles.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if ( response.isSuccessful() ) {
                    listaInmuebleMutable.postValue(response.body());
                } else  {
                    Toast.makeText(context, "No hay inmuebles", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Toast.makeText(context, "Ocurrio algun error", Toast.LENGTH_LONG).show();
            }
        });
        /*
        ApiClient api = ApiClient.getApi();
        List<Inmueble> inmuebleList = api.obtnerPropiedades();
        listaInmuebleMutable.setValue(inmuebleList);*/
    }

    public void cargarInmueblesAlquilados() {
        ApiClient api = ApiClient.getApi();
        List<Inmueble> inmuebleList = api.obtenerPropiedadesAlquiladas();
        listaInmuebleMutable.setValue(inmuebleList);
    }



}
