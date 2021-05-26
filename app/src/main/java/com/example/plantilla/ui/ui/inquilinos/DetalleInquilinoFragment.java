package com.example.plantilla.ui.ui.inquilinos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.plantilla.R;
import com.example.plantilla.modelo.Contrato;
import com.example.plantilla.modelo.Inquilino;

public class DetalleInquilinoFragment extends Fragment {

    private DetalleInquilinoViewModel detalleInquilinoViewModel;
    private Inquilino inquilinoActual;
    private View root;
    private EditText etCodigoInquilino, etNombreInquilino, etApellidoInquilino, etDniInquilino, etEmailInquilino, etTelefonoInquilino, etGarante, etTelefonoGarante;

    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        detalleInquilinoViewModel = new ViewModelProvider(this).get(DetalleInquilinoViewModel.class);
        root = inflater.inflate(R.layout.fragment_detalle_inquilino, container, false);

        inicializarComponentes(root);

        int ContratoId = getArguments().getInt("ContratoId");

        detalleInquilinoViewModel.getDetalleInquilinoMutable().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {

                inquilinoActual = inquilino;

                etCodigoInquilino.setText(inquilino.getId()+"");
                etNombreInquilino.setText(inquilino.getNombre());
                etApellidoInquilino.setText(inquilino.getApellido());
                etDniInquilino.setText(inquilino.getDNI()+"");
                etEmailInquilino.setText(inquilino.getEmail());
                etTelefonoInquilino.setText(inquilino.getTelefono());
                etGarante.setText(inquilino.getNombreGarante());
                etTelefonoGarante.setText(inquilino.getTelefonoGarante());
            }
        });

        detalleInquilinoViewModel.cargarDetalleAlquiler(ContratoId);

        return root;
    }

    private void inicializarComponentes(View root) {
        etCodigoInquilino = root.findViewById(R.id.etCodigoInquilino);
        etNombreInquilino = root.findViewById(R.id.etNombreInquilino);
        etApellidoInquilino = root.findViewById(R.id.etApellidoInquilino);
        etDniInquilino = root.findViewById(R.id.etDniInquilino);
        etEmailInquilino = root.findViewById(R.id.etEmailInquilino);
        etTelefonoInquilino = root.findViewById(R.id.etTelefonoInquilino);
        etGarante = root.findViewById(R.id.etGarante);
        etTelefonoGarante = root.findViewById(R.id.etTelefonoGarante);
    }

}