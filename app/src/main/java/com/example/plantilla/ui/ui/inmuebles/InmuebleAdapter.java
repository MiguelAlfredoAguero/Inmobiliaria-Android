package com.example.plantilla.ui.ui.inmuebles;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.plantilla.R;
import com.example.plantilla.modelo.Inmueble;
import com.example.plantilla.request.ApiClient;

import org.w3c.dom.Text;

import java.util.List;

public class InmuebleAdapter extends ArrayAdapter<Inmueble> {
    private Context context;
    private List<Inmueble> inmuebles;
    private LayoutInflater layoutInflater;

    public InmuebleAdapter(@NonNull Context context, int resource, @NonNull List<Inmueble> objects, LayoutInflater layoutInflater) {
        super(context, resource, objects);
        this.context = context;
        this.inmuebles = objects;
        this.layoutInflater = layoutInflater;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView =  convertView;

        if ( itemView==null) {
            itemView = layoutInflater.inflate(R.layout.item_inmueble, parent, false);
        }

        Inmueble inmueble = inmuebles.get(position);

        ImageView fotoInmueble = itemView.findViewById(R.id.ivFotoInmuebleL);
        TextView tvDireccion = itemView.findViewById(R.id.tvDireccionInmuebleL);
        TextView tvPrecio = itemView.findViewById(R.id.tvPrecioInmuebleL);

        Glide.with(getContext())
                .load(ApiClient.SERVER+inmueble.getavatar())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(fotoInmueble);

        //.placeholder(@Drawable.) colocar aqui una imagen por defecto


        tvDireccion.setText(inmueble.getDireccion());
        tvPrecio.setText(inmueble.getPrecio()+"");

        return itemView;
    }
}
