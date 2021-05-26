package com.example.plantilla.ui.ui.pagos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.plantilla.R;
import com.example.plantilla.modelo.Pago;

import java.util.List;

public class PagoAdapter extends ArrayAdapter<Pago> {

    private Context context;
    private List<Pago> pagos;
    private LayoutInflater layoutInflater;

    public PagoAdapter(@NonNull Context context, int resource, @NonNull List<Pago> objects, LayoutInflater layoutInflater) {
        super(context, resource, objects);
        this.context = context;
        this.pagos = objects;
        this.layoutInflater = layoutInflater;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView =  convertView;

        if ( itemView==null) {
            itemView = layoutInflater.inflate(R.layout.item_pago, parent, false);
        }

        Pago pago = pagos.get(position);

        TextView tvCodigoPago = itemView.findViewById(R.id.tvCodigoPago);
        TextView tvNumeroPago = itemView.findViewById(R.id.tvNumeroPago);
        TextView tvCodigoContratoPago = itemView.findViewById(R.id.tvCodigoContratoPago);
        TextView tvImportePago = itemView.findViewById(R.id.tvImportePago);
        TextView tvFechaPago = itemView.findViewById(R.id.tvFechaPago);

        tvCodigoPago.setText(pago.getIdPago()+"");
        tvNumeroPago.setText(pago.getNumero()+"");
        tvCodigoContratoPago.setText(pago.getContrato().getContratoId()+"");
        tvImportePago.setText(pago.getImporte()+"");
        tvFechaPago.setText(pago.getFechaDePago());

        return itemView;
    }




}
