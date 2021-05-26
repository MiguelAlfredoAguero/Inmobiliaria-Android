package com.example.plantilla.ui.ui.contratos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Contrato;
import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.request.ApiClient;

import java.util.List;

public class ContratosVigentesViewModel extends ViewModel {
    private MutableLiveData <List<Inmueble>> listaInmueblesMutable;
    private MutableLiveData <Contrato> contratoMutable;

    public LiveData<List<Inmueble>> getListaInmueblesMutable() {
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
        ApiClient api = ApiClient.getApi();
        List<Inmueble> inmuebleList = api.obtenerPropiedadesAlquiladas();
        listaInmueblesMutable.setValue(inmuebleList);
    }

    public void obtenerContrato(Inmueble inmueble) {
        ApiClient apiClient = ApiClient.getApi();
        Contrato contrato = apiClient.obtenerContratoVigente(inmueble);
        contratoMutable.setValue(contrato);
    }

}
