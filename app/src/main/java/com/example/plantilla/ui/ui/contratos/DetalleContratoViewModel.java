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
import com.example.plantilla.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleContratoViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> contratoMutable;
    private Context context;

    public DetalleContratoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Contrato> getContratoMutable() {
        if ( contratoMutable == null) {
            contratoMutable = new MutableLiveData<>();
        }
        return contratoMutable;
    }

    public void cargarContratoVigente(int ContratoId) {

        Call<Contrato> detalleContrato = ApiClient.getMyApiClient().detalleContrato(ContratoId, ApiClient.obtenerToken(context));
        detalleContrato.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if ( response.isSuccessful() ) {
                    contratoMutable.setValue(response.body());
                } else {
                    Log.d("msj", "Contrato no encontrado");
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                Log.d("msj", "OCURRIO UN ERROR");
            }
        });


    }

}
