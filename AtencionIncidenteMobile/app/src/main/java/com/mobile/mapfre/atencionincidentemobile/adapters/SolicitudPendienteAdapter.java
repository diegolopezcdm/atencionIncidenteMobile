package com.mobile.mapfre.atencionincidentemobile.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.mapfre.atencionincidentemobile.R;
import com.mobile.mapfre.atencionincidentemobile.SolicitudPendienteFindActivity;
import com.mobile.mapfre.atencionincidentemobile.model.SolicitudPentiente;

import java.util.List;

/**
 * Created by diego on 11/09/16.
 */
public class SolicitudPendienteAdapter extends RecyclerView.Adapter<SolicitudPendienteAdapter.SolicitudPendienteViewHolder> {

    private List<SolicitudPentiente> items;

    @Override
    public SolicitudPendienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_solicitud_pendiente_detalle, parent, false);
        SolicitudPendienteViewHolder pvh = new SolicitudPendienteViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(SolicitudPendienteViewHolder holder, final int position) {
        holder.numeroCti.setText(items.get(position).getNumeroCti());
        holder.proceso.setText(items.get(position).getProceso());
        holder.tipoSolicitud.setText(items.get(position).getTipoSolicitud());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent solicitudPendienteIntent = new Intent(view.getContext(), SolicitudPendienteFindActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("incidenteId", items.get(position).getId().toString());


                /*bundle.putString("numeroCti", items.get(position).getNumeroCti());
                bundle.putString("proceso", items.get(position).getProceso());
                bundle.putString("tipoSolicitud", items.get(position).getTipoSolicitud());

                items.get(position).setMatricula("test matricula");
                items.get(position).setSistema("test sistema");
                items.get(position).setSubProceso("test subproceso");
                items.get(position).setAsunto("test asunto");
                items.get(position).setDescripcion("test desc");
                items.get(position).setDatosAModificar("test datos");

                bundle.putString("matricula", items.get(position).getMatricula());
                bundle.putString("sistena", items.get(position).getSistema());
                bundle.putString("subproceso", items.get(position).getSubProceso());
                bundle.putString("asunto", items.get(position).getAsunto());
                bundle.putString("descripcion", items.get(position).getDescripcion());
                bundle.putString("datosAModificar", items.get(position).getDatosAModificar());
                bundle.putString("tipoSolicitud", items.get(position).getTipoSolicitud());*/

                solicitudPendienteIntent.putExtras(bundle);
                view.getContext().startActivity(solicitudPendienteIntent);
            }
        });
    }

    public SolicitudPendienteAdapter(List<SolicitudPentiente> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class SolicitudPendienteViewHolder extends RecyclerView.ViewHolder{

        CardView cv;
        TextView numeroCti;
        TextView proceso;
        TextView tipoSolicitud;

        public SolicitudPendienteViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.solicitudPendienteCv);
            numeroCti = (TextView)itemView.findViewById(R.id.numeroCtiTxt);
            proceso = (TextView)itemView.findViewById(R.id.procesoTxt);
            tipoSolicitud = (TextView)itemView.findViewById(R.id.tipoSolicitudTxt);

        }
    }
}
