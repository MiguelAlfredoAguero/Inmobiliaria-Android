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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.plantilla.R;
import com.example.plantilla.modelo.Contrato;
import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.modelo.Inquilino;
import com.example.plantilla.ui.ui.inquilinos.InquilinoAdapter;
import com.example.plantilla.ui.ui.inquilinos.InquilinosViewModel;

import java.util.List;

public class ContratosVigentesFragment extends Fragment {

    private ContratosVigentesViewModel contratosVigentesViewModel;
    private List<Contrato> listaInmueblesAlquilados;
    private View root;

    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        contratosVigentesViewModel = new ViewModelProvider(this).get(ContratosVigentesViewModel.class);
        root = inflater.inflate(R.layout.fragment_contratos_vigentes, container, false);

        contratosVigentesViewModel.getListaInmueblesMutable().observe(getViewLifecycleOwner(), new Observer<List<Contrato>>() {
            @Override
            public void onChanged(List<Contrato> inmuebles) {
                listaInmueblesAlquilados = inmuebles;
                generarView(inflater, root);
            }
        });


        contratosVigentesViewModel.cargarInmueblesAlquilados();

        return root;
    }


    private void generarView(LayoutInflater layoutInflater, View root) {
        // Estamos reutilizando la vista del item inquilino y su adapter
        ArrayAdapter<Contrato> arrayAdapter = new ContratoAdapter(getContext(), R.layout.item_contrato, listaInmueblesAlquilados, layoutInflater);
        final ListView listView = root.findViewById(R.id.lvContratosVigentes);

        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Contrato contrato = listaInmueblesAlquilados.get(position);

                //contratosVigentesViewModel.obtenerContrato(inmueble);

                Bundle bundle = new Bundle();
                //bundle.putSerializable("contratoVigente", contratoVigente);
                bundle.putInt("ContratoId", contrato.getId());

                Navigation.findNavController(view).navigate(R.id.action_contratosVigentesFragment_to_detalleContratoFragment, bundle);

            }
        });

    }

}