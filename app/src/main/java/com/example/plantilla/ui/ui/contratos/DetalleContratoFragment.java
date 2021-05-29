package com.example.plantilla.ui.ui.contratos;

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
import android.widget.Button;
import android.widget.EditText;

import com.example.plantilla.R;
import com.example.plantilla.modelo.Contrato;
import com.example.plantilla.modelo.Inquilino;
import com.example.plantilla.ui.ui.inquilinos.DetalleInquilinoViewModel;

public class DetalleContratoFragment extends Fragment {

    // Cambiar ViewModel
    private DetalleContratoViewModel detalleContratoViewModel;
    private Contrato contratoVigente;
    private View root;
    private EditText etCodigoContrato, etFInicioContrato, etFFinContrato, etMontoContrato, etInquilinoContrato, etInmuebleContrato;
    private Button btnPagosContrato;

    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        detalleContratoViewModel = new ViewModelProvider(this).get(DetalleContratoViewModel.class);
        root = inflater.inflate(R.layout.fragment_detalle_contrato, container, false);

        inicializarComponentes(root);

        int ContratoId = getArguments().getInt("ContratoId");


        detalleContratoViewModel.getContratoMutable().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {

                contratoVigente = contrato;

                etCodigoContrato.setText(contrato.getId()+"");
                etFInicioContrato.setText(contrato.getDesde());
                etFFinContrato.setText(contrato.getHasta());
                etMontoContrato.setText(contrato.getPrecio()+"");
                etInquilinoContrato.setText(contrato.getInquilino().getPersona().getApellido() +" "+contrato.getInquilino().getPersona().getNombre());
                etInmuebleContrato.setText("Inmueble en: " + contrato.getInmueble().getDireccion());

            }
        });

        detalleContratoViewModel.cargarContratoVigente(ContratoId);

        return root;
    }

    private void inicializarComponentes(View root) {
        etCodigoContrato = root.findViewById(R.id.etCodigoContrato);
        etFInicioContrato = root.findViewById(R.id.etFInicioContrato);
        etFFinContrato = root.findViewById(R.id.etFFinContrato);
        etMontoContrato = root.findViewById(R.id.etMontoContrato);
        etInquilinoContrato = root.findViewById(R.id.etInquilinoContrato);
        etInmuebleContrato = root.findViewById(R.id.etInmuebleContrato);
        btnPagosContrato = root.findViewById(R.id.btnPagosContrato);

        btnPagosContrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                //bundle.putSerializable("contratoVigente", contratoVigente);
                int ContratoId = Integer.parseInt(etCodigoContrato.getText().toString());
                bundle.putInt("ContratoId", ContratoId);

                Navigation.findNavController(v).navigate(R.id.action_detalleContratoFragment_to_pagosFragment, bundle);
            }
        });
    }

}