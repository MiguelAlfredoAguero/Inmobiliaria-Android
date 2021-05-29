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

public class DetalleInquilinoViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> detalleInquilinoMutable;
    private Context context;

    public DetalleInquilinoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Contrato> getDetalleInquilinoMutable() {
        if (detalleInquilinoMutable == null) {
            detalleInquilinoMutable = new MutableLiveData<>();
        }
        return detalleInquilinoMutable;
    }

    public void cargarDetalleAlquiler(int contratoId) {

        Call<Contrato> detalleAlquiler = ApiClient.getMyApiClient().detalleAlquiler(contratoId, ApiClient.obtenerToken(context));
        detalleAlquiler.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if ( response.isSuccessful() ) {
                    detalleInquilinoMutable.postValue(response.body());
                } else {
                    Log.d("msj", "cargarDetalleAlquiler(): No encontrado.");
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                Log.d("msj", "OCURRIO ALGUN ERROR.");
            }
        });

        /*
        detalleInquilinoMutable.setValue(inquilino);
        */
    }
}
