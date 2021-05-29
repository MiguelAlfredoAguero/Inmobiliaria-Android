package com.example.plantilla.ui.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Contrato;
import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.modelo.Inquilino;
import com.example.plantilla.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinosViewModel extends AndroidViewModel {

    private MutableLiveData<List<Contrato>> listaInmuebleMutable;
    private MutableLiveData<Inquilino> inquilinoMutable;
    private Context context;

    public InquilinosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<List<Contrato>> getListaInmuebleMutable () {
        if ( listaInmuebleMutable == null ) {
            listaInmuebleMutable = new MutableLiveData<>();
        }
        return listaInmuebleMutable;
    }
    public LiveData<Inquilino> getInquilinoMutable () {
        if ( inquilinoMutable == null ) {
            inquilinoMutable = new MutableLiveData<>();
        }
        return inquilinoMutable;
    }


    public void cargarInmueblesAlquilados() {

        Call<List<Contrato>> listaAlquileres = ApiClient.getMyApiClient().listaAlquileres(ApiClient.obtenerToken(context));
        listaAlquileres.enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                if ( response.isSuccessful() ) {
                    listaInmuebleMutable.postValue(response.body());
                } else {
                    Log.d("msj", "cargarInmueblesAlquilados(): No encontrado.");
                }
            }

            @Override
            public void onFailure(Call<List<Contrato>> call, Throwable t) {
                Log.d("msj", "OCURRIO ALGUN ERROR.");
            }
        });


        /*
        ApiClient api = ApiClient.getApi();
        List<Inmueble> inmuebleList = api.obtenerPropiedadesAlquiladas();
        listaInmuebleMutable.setValue(inmuebleList);
        */
    }

    /*
    public void obtenerInquilino(Inmueble inmueble) {
        ApiClient api = ApiClient.getApi();
        Inquilino inquilino = api.obtenerInquilino(inmueble);
        inquilinoMutable.setValue(inquilino);
    }
    */

}
