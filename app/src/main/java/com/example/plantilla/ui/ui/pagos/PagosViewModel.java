package com.example.plantilla.ui.ui.pagos;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Contrato;
import com.example.plantilla.modelo.Pago;
import com.example.plantilla.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosViewModel extends AndroidViewModel {
    private MutableLiveData<List<Pago>> listaPagosMutable;
    private Context context;

    public PagosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<List<Pago>> getListaPagosMutable() {
        if ( listaPagosMutable == null) {
            listaPagosMutable = new MutableLiveData<>();
        }
        return listaPagosMutable;
    }

    public void cargarPagos(int ContratoId) {

        Call<List<Pago>> listarPagos = ApiClient.getMyApiClient().listaPagos(ContratoId, ApiClient.obtenerToken(context));
        listarPagos.enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                if ( response.isSuccessful() ) {
                    listaPagosMutable.setValue(response.body());
                } else {
                    Log.d("msj", "Pagos NO encontrados ");
                }
            }

            @Override
            public void onFailure(Call<List<Pago>> call, Throwable t) {
                Log.d("msj", "OCURRIO UN ERROR");
            }
        });

        /*
        ApiClient apiClient = ApiClient.getApi();
        List<Pago> listaPagos = apiClient.obtenerPagos(contrato);
        listaPagosMutable.setValue(listaPagos);
        */
    }
}
