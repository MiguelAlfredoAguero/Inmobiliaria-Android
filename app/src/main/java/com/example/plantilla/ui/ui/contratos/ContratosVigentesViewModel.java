package com.example.plantilla.ui.ui.contratos;

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
import com.example.plantilla.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratosVigentesViewModel extends AndroidViewModel {
    private MutableLiveData <List<Contrato>> listaInmueblesMutable;
    private Context context;

    public ContratosVigentesViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<List<Contrato>> getListaInmueblesMutable() {
        if ( listaInmueblesMutable == null) {
            listaInmueblesMutable = new MutableLiveData<>();
        }
        return listaInmueblesMutable;
    }


    public void cargarInmueblesAlquilados() {

        Call<List<Contrato>> listaContratos = ApiClient.getMyApiClient().listaContratos(ApiClient.obtenerToken(context));
        listaContratos.enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                if ( response.isSuccessful() ) {
                    listaInmueblesMutable.postValue(response.body());
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
        listaInmueblesMutable.setValue(inmuebleList);
        */
    }


}
