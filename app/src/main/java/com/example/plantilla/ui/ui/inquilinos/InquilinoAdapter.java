package com.example.plantilla.ui.ui.inquilinos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.plantilla.R;
import com.example.plantilla.modelo.Contrato;
import com.example.plantilla.request.ApiClient;

import java.util.List;

public class InquilinoAdapter extends ArrayAdapter<Contrato> {
    private Context context;
    private List<Contrato> inmueblesAlquilados;
    private LayoutInflater layoutInflater;

    public InquilinoAdapter(@NonNull Context context, int resource, @NonNull List<Contrato> objects, LayoutInflater layoutInflater) {
        super(context, resource, objects);
        this.context = context;
        this.inmueblesAlquilados = objects;
        this.layoutInflater = layoutInflater;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView =  convertView;

        if ( itemView==null) {
            itemView = layoutInflater.inflate(R.layout.item_inquilino, parent, false);
        }

        Contrato contrato = inmueblesAlquilados.get(position);

        ImageView fotoInmueble = itemView.findViewById(R.id.ivFotoInmuebleL);
        TextView tvDireccion = itemView.findViewById(R.id.tvDireccionInmuebleL);
        EditText etInquilino = itemView.findViewById(R.id.etContratoId);

        Glide.with(getContext())
                .load(ApiClient.SERVER+contrato.getInmueble().getavatar())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //.placeholder(@Drawable.) colocar aqui una imagen por defecto
                .into(fotoInmueble);

        etInquilino.setText(contrato.getInquilino().getPersona().getApellido() + " " + contrato.getInquilino().getPersona().getNombre());
        tvDireccion.setText(contrato.getInmueble().getDireccion());

        return itemView;
    }
}
