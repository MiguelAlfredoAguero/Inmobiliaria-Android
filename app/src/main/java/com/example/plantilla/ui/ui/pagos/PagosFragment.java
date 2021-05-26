package com.example.plantilla.ui.ui.pagos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.plantilla.R;
import com.example.plantilla.modelo.Contrato;
import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.modelo.Pago;
import com.example.plantilla.ui.ui.inmuebles.InmuebleAdapter;
import com.example.plantilla.ui.ui.inmuebles.InmueblesViewModel;

import java.util.List;

public class PagosFragment extends Fragment {

    private PagosViewModel pagosViewModel;
    private List<Pago> listaPagos;
    private Contrato contratoVigente;
    private View root;

    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        pagosViewModel = new ViewModelProvider(this).get(PagosViewModel.class);
        root = inflater.inflate(R.layout.fragment_pagos, container, false);

        contratoVigente = (Contrato) getArguments().getSerializable("contratoVigente");

        pagosViewModel.getListaPagosMutable().observe(getViewLifecycleOwner(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> pagos) {
                listaPagos = pagos;
                generarView(inflater, root);
            }
        });

        pagosViewModel.cargarPagos(contratoVigente);

        return root;
    }

    private void generarView(LayoutInflater layoutInflater, View root) {
        final ArrayAdapter<Pago> arrayAdapter = new PagoAdapter(getContext(), R.layout.item_pago, listaPagos, layoutInflater);
        final ListView listView = root.findViewById(R.id.lvPagos);

        listView.setAdapter(arrayAdapter);

    }

}