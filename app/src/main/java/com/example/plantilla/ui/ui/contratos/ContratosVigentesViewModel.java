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
    private MutableLiveData <Contrato> contratoMutable;
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
    public LiveData<Contrato> getContratoMutable() {
        if ( contratoMutable == null) {
            contratoMutable = new MutableLiveData<>();
        }
        return contratoMutable;
    }

    public void cargarInmueblesAlquilados() {

        Call<List<Contrato>> listaContratos = ApiClient.getMyApiClient().listaContratos(ApiClient.obtenerToken(context));
        listaContratos.enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                if ( response.isSuccessful() ) {
                    listaInmueblesMutable.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Contrato>> call, Throwable t) {

            }
        });

        /*
        ApiClient api = ApiClient.getApi();
        List<Inmueble> inmuebleList = api.obtenerPropiedadesAlquiladas();
        listaInmueblesMutable.setValue(inmuebleList);
        */
    }


    /*
    public void obtenerContrato(Inmueble inmueble) {

        Call<Contrato> detalleContrato = ApiClient.getMyApiClient().detalleContrato(inmueble.getid(), ApiClient.obtenerToken(context));
        detalleContrato.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if ( response.isSuccessful() ) {
                    Log.d("msj", "Id del contrato ViewModel " + response.body().getContratoId());
                    contratoMutable.setValue(response.body());
                } else {
                    Log.d("msj", "Contrato no encontrado " );
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                Log.d("msj", "OCURRIO ALGUN ERROR " );
            }
        });


        ApiClient apiClient = ApiClient.getApi();
        Contrato contrato = apiClient.obtenerContratoVigente(inmueble);
        contratoMutable.setValue(contrato);

    }
    */

}
