package solutions.empire42.tatianego.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import solutions.empire42.tatianego.R;
import solutions.empire42.tatianego.model.Historico;

import java.text.SimpleDateFormat;
import java.util.List;

public class EncomendaAdapter extends RecyclerView.Adapter {

    private List<Historico> encomendas;
    private Context context;
    private OnEncomendaItemClickListener listener;
    private OnEncomendaCheckClickListener checker;

    public interface OnEncomendaItemClickListener {
        void onItemClick(Historico item);
    }

    public interface OnEncomendaCheckClickListener {
        void onItemClick(Historico item);
    }

    public EncomendaAdapter(List<Historico> encomendas, Context context, OnEncomendaItemClickListener listener, OnEncomendaCheckClickListener checker) {
        this.encomendas = encomendas;
        this.context = context;
        this.listener = listener;
        this.checker = checker;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.encomendas_recycle_view, viewGroup, false);

        EncomendaViewHolder encomendaViewHolder = new EncomendaViewHolder(view);

        return encomendaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        EncomendaViewHolder holder = (EncomendaViewHolder) viewHolder;
        holder.bind(encomendas.get(i),listener, checker);
    }

    @Override
    public int getItemCount() {
        return this.encomendas.size();
    }

    public class EncomendaViewHolder extends RecyclerView.ViewHolder {

        final TextView tituloEncomenda;
        final TextView dataHoraEncomenda;
        final TextView usuarioEncomenda;
        final Switch ativoEncomenda;

        public EncomendaViewHolder(@NonNull View itemView) {
            super(itemView);

            tituloEncomenda = itemView.findViewById(R.id.titulo_recycle_encomenda);
            usuarioEncomenda = itemView.findViewById(R.id.nome_recycle_encomenda);
            dataHoraEncomenda = itemView.findViewById(R.id.dataHora_recycle_encomenda);
            ativoEncomenda = itemView.findViewById(R.id.switch_encomenda);

        }

        public void bind( final Historico item, final OnEncomendaItemClickListener listener, final OnEncomendaCheckClickListener checker ) {
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            tituloEncomenda.setText(item.getProduto());
            usuarioEncomenda.setText(item.getUsuario());
            dataHoraEncomenda.setText(simpleDateFormat.format(item.getDataHora()));
            ativoEncomenda.setChecked(item.getAtivo());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

            ativoEncomenda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checker.onItemClick(item);

                }
            });
        }
    }
}
