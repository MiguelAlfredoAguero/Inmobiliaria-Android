package com.example.plantilla.ui.ui.contratos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

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
    private List<Inmueble> listaInmueblesAlquilados;
    private Contrato contratoVigente;
    private View root;

    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        contratosVigentesViewModel = new ViewModelProvider(this).get(ContratosVigentesViewModel.class);
        root = inflater.inflate(R.layout.fragment_contratos_vigentes, container, false);

        contratosVigentesViewModel.getListaInmueblesMutable().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                listaInmueblesAlquilados = inmuebles;
                generarView(inflater, root);
            }
        });
        contratosVigentesViewModel.getContratoMutable().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                contratoVigente = contrato;
            }
        });

        contratosVigentesViewModel.cargarInmueblesAlquilados();

        return root;
    }


    private void generarView(LayoutInflater layoutInflater, View root) {
        // Estamos reutilizando la vista del item inquilino y su adapter
        ArrayAdapter<Inmueble> arrayAdapter = new InquilinoAdapter(getContext(), R.layout.item_inquilino, listaInmueblesAlquilados, layoutInflater);
        final ListView listView = root.findViewById(R.id.lvContratosVigentes);

        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Inmueble inmueble = listaInmueblesAlquilados.get(position);

                // obtenerContratoVigente

                // 1- llamda del metodo al ViewModel(inmueble)
                contratosVigentesViewModel.obtenerContrato(inmueble);

                // 2- Mutabel en el ViewModel

                // 3- El observer lo ponemos en onCreateView

                // 4- Setear resultado del observer en un ATRIBUTO (un inquilino)

                // 5- Utilizar referencia del ATRIBUTO


                Bundle bundle = new Bundle();
                bundle.putSerializable("contratoVigente", contratoVigente);

                Navigation.findNavController(view).navigate(R.id.action_contratosVigentesFragment_to_detalleContratoFragment, bundle);

            }
        });

    }

}