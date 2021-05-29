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
    private MutableLiveData<Inquilino> detalleInquilinoMutable;
    private Context context;

    public DetalleInquilinoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Inquilino> getDetalleInquilinoMutable() {
        if (detalleInquilinoMutable == null) {
            detalleInquilinoMutable = new MutableLiveData<>();
        }
        return detalleInquilinoMutable;
    }

    public void cargarDetalleAlquiler(int contratoId) {

        Call<Inquilino> detalleAlquiler = ApiClient.getMyApiClient().detalleAlquiler(contratoId, ApiClient.obtenerToken(context));
        detalleAlquiler.enqueue(new Callback<Inquilino>() {
            @Override
            public void onResponse(Call<Inquilino> call, Response<Inquilino> response) {
                if ( response.isSuccessful() ) {
                    detalleInquilinoMutable.setValue(response.body());
                } else {
                    Log.d("msj", "cargarDetalleAlquiler(): No encontrado.");
                }
            }

            @Override
            public void onFailure(Call<Inquilino> call, Throwable t) {
                Log.d("msj", "OCURRIO ALGUN ERROR.");
            }
        });

        /*
        detalleInquilinoMutable.setValue(inquilino);
        */
    }
}
