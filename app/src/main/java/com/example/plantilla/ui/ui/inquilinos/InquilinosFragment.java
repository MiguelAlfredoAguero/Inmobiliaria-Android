package com.example.plantilla.ui.ui.inquilinos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.plantilla.R;
import com.example.plantilla.modelo.Contrato;
import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.modelo.Inquilino;

import java.util.List;

public class InquilinosFragment extends Fragment {

    private InquilinosViewModel inquilinosViewModel;
    private List<Contrato> listaInmueblesAlquilados;
    private View root;

    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        inquilinosViewModel = new ViewModelProvider(this).get(InquilinosViewModel.class);
        root = inflater.inflate(R.layout.fragment_inquilinos, container, false);

        inquilinosViewModel.getListaInmuebleMutable().observe(getViewLifecycleOwner(), new Observer<List<Contrato>>() {
            @Override
            public void onChanged(List<Contrato> contratos) {
                listaInmueblesAlquilados = contratos;
                generarView(inflater, root);
            }
        });

        inquilinosViewModel.cargarInmueblesAlquilados();

        return root;
    }

    private void generarView(LayoutInflater layoutInflater, View root) {
        ArrayAdapter<Contrato> arrayAdapter = new InquilinoAdapter(getContext(), R.layout.item_inquilino, listaInmueblesAlquilados, layoutInflater);
        final ListView listView = root.findViewById(R.id.lvInmueblesAlquilados);

        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Contrato contrato = listaInmueblesAlquilados.get(position);
                // el Id que viene en inmueble es el del contrato

                Bundle bundle = new Bundle();
                //bundle.putSerializable("inquilinoActual", inquilinoActual);
                bundle.putInt("ContratoId", contrato.getId());

                Navigation.findNavController(view).navigate(R.id.action_inquilinosFragment_to_detalleInquilinoFragment, bundle);

            }
        });

    }
}