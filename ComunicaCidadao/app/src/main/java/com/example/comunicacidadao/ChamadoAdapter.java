package com.example.comunicacidadao;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import modelDominio.Chamado;

public class ChamadoAdapter extends RecyclerView.Adapter<ChamadoAdapter.MyViewHolder>{
    private ArrayList<Chamado> listaChamados;
    private ChamadoOnClickListener chamadoOnClickListener;

    public ChamadoAdapter(ArrayList<Chamado> listaChamados, ChamadoOnClickListener chamadoOnClickListener) {
        this.listaChamados = listaChamados;
        this.chamadoOnClickListener = chamadoOnClickListener;
    }

    @Override
    public ChamadoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ChamadoAdapter.MyViewHolder holder, final int position) {

        Chamado meuChamado = listaChamados.get(position);


        //vendo qual o status do chamado para mostrar na tela como string
        String status = null;
        if (meuChamado.getStatus() == 0) {
            status = "Não Solucionado";
        } else if (meuChamado.getStatus() == 1) {
            status = "Em processo para solucionar";
        } else if (meuChamado.getStatus() == 2) {
            status = "Solucionado";
        }

        holder.tvItemListRowTitulo.setText(meuChamado.getTitulo());
        holder.tvItemListRowSetor.setText(meuChamado.getSetor());
        holder.tvItemListRowStatus.setText(status);
        /* CUIDADO: .setText() precisa sempre de String. Se for outro tipo de dado, deve ser feita a conversão com o String.valueOf() */

        // clique no item do cliente
        if (chamadoOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chamadoOnClickListener.onClickChamado(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaChamados.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemListRowTitulo, tvItemListRowSetor, tvItemListRowStatus;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvItemListRowTitulo = (TextView) itemView.findViewById(R.id.tvItemListRowTitulo);
            tvItemListRowSetor = (TextView) itemView.findViewById(R.id.tvItemListRowSetor);
            tvItemListRowStatus = (TextView) itemView.findViewById(R.id.tvItemListRowStatus);


        }
    }

    public interface ChamadoOnClickListener {
        public void onClickChamado(View view, int position);
    }

}
