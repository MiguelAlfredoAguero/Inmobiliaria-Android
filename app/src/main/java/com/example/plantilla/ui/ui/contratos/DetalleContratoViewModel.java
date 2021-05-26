package com.example.plantilla.ui.ui.contratos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Contrato;

public class DetalleContratoViewModel extends ViewModel {
    private MutableLiveData<Contrato> contratoMutable;

    public LiveData<Contrato> getContratoMutable() {
        if ( contratoMutable == null) {
            contratoMutable = new MutableLiveData<>();
        }
        return contratoMutable;
    }

    public void cargarContratoVigente(Contrato contrato) {
        contratoMutable.setValue(contrato);
    }

}
