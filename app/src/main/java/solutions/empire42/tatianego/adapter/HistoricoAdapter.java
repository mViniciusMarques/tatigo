package solutions.empire42.tatianego.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import solutions.empire42.tatianego.R;
import solutions.empire42.tatianego.model.Historico;

import java.text.SimpleDateFormat;
import java.util.List;

public class HistoricoAdapter extends RecyclerView.Adapter {

    List<Historico> historicos;
    Context context;

    public HistoricoAdapter(List<Historico> historicos, Context context) {
        this.historicos = historicos;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.historico_recycle_view, viewGroup, false);

        HistoricoViewHolder historicoViewHolder= new HistoricoViewHolder(view);

        return historicoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            HistoricoViewHolder holder = (HistoricoViewHolder) viewHolder;
            Historico historico = historicos.get(i);
            holder.tituloProduto.setText(historico.getProduto());
            holder.usuarioProduto.setText(historico.getUsuario());
            holder.dataHoraProduto.setText(  simpleDateFormat.format(historico.getDataHora())  );
            holder.ativoProduto.setText(historico.getAtivo());
    }

    @Override
    public int getItemCount() {
        return historicos.size();
    }

    public class HistoricoViewHolder extends RecyclerView.ViewHolder {

        final TextView tituloProduto;
        final TextView usuarioProduto;
        final TextView dataHoraProduto;
        final TextView ativoProduto;

        public HistoricoViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloProduto =  itemView.findViewById(R.id.produto_titulo_historico);
            usuarioProduto = itemView.findViewById(R.id.produto_usuario_produto);
            dataHoraProduto = itemView.findViewById(R.id.produto_datahora_historico);
            ativoProduto = itemView.findViewById(R.id.produto_ativo_historico);
        }
    }
}
