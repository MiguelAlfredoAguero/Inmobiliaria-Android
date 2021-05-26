package com.example.plantilla.ui.ui.pagos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Contrato;
import com.example.plantilla.modelo.Pago;
import com.example.plantilla.request.ApiClient;

import java.util.List;

public class PagosViewModel extends ViewModel {
    private MutableLiveData<List<Pago>> listaPagosMutable;

    public LiveData<List<Pago>> getListaPagosMutable() {
        if ( listaPagosMutable == null) {
            listaPagosMutable = new MutableLiveData<>();
        }
        return listaPagosMutable;
    }

    public void cargarPagos(Contrato contrato) {
        ApiClient apiClient = ApiClient.getApi();
        List<Pago> listaPagos = apiClient.obtenerPagos(contrato);
        listaPagosMutable.setValue(listaPagos);
    }
}
