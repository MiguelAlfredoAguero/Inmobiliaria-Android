package com.example.plantilla.ui.ui.inmuebles;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.plantilla.R;
import com.example.plantilla.modelo.Inmueble;

import java.io.Serializable;
import java.util.List;

public class DetalleInmuebleFragment extends Fragment {

    private DetalleInmuebleViewModel detalleInmuebleViewModel;
    private View root;
    private EditText etCodigoInmueble, etAmbientesInmueble, etDireccionInmueble, etPrecioInmueble, etUsoInmueble, etTipoInmueble;
    private CheckBox cbDisponibleInmueble;
    private ImageView ivFotoInmueble;
    private Inmueble inmueble;

    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        int InmuebleId = getArguments().getInt("InmuebleId");

        detalleInmuebleViewModel = new ViewModelProvider(this).get(DetalleInmuebleViewModel.class);
        root = inflater.inflate(R.layout.fragment_detalle_inmueble, container, false);

        inicializarComponentes(root);

        detalleInmuebleViewModel.getDetalleInmuebleMutable().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inm) {
                inmueble = new Inmueble();
                inmueble = inm;
                etCodigoInmueble.setText(inmueble.getId()+"");
                etAmbientesInmueble.setText(inmueble.getAmbientes()+"");
                etDireccionInmueble.setText(inmueble.getDireccion());
                etPrecioInmueble.setText(inmueble.getPrecio()+"");
                etUsoInmueble.setText(inmueble.getUsoInmueble().getNombre());
                etTipoInmueble.setText(inmueble.getTipoInmueble().getNombre());

                cbDisponibleInmueble.setChecked(inmueble.isDisponible());

                Glide.with(getContext())
                        .load("http://192.168.1.105:45455"+inmueble.getavatar())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(ivFotoInmueble);

            }
        });

        detalleInmuebleViewModel.cargarDetalleInmueble(InmuebleId);

        return root;
    }

    private void inicializarComponentes(View root) {
        etCodigoInmueble = root.findViewById(R.id.etCodigoInmueble);
        etAmbientesInmueble = root.findViewById(R.id.etAmbientesInmueble);
        etDireccionInmueble = root.findViewById(R.id.etDireccionInmueble);
        etPrecioInmueble = root.findViewById(R.id.etPrecioInmueble);
        etUsoInmueble = root.findViewById(R.id.etUsoInmueble);
        etTipoInmueble = root.findViewById(R.id.etTipoInmueble);
        cbDisponibleInmueble = root.findViewById(R.id.cbDisponibleInmueble);
        ivFotoInmueble = root.findViewById(R.id.ivFotoInmueble);

        cbDisponibleInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //inmueble.setDisponible(cbDisponibleInmueble.isChecked());

                detalleInmuebleViewModel.actualizarDetalleInmueble(inmueble.getId());

                Toast.makeText(getContext(), "Inmueble actualizado con exito.", Toast.LENGTH_LONG).show();

            }
        });

    }



    ////////////////////////////
/*
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("inmueble", inmueble);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("msj", "onDestroyView ");
    }
 */

}