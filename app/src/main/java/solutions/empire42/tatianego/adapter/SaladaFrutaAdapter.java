package solutions.empire42.tatianego.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import solutions.empire42.tatianego.R;
import solutions.empire42.tatianego.model.Produto;

import java.text.SimpleDateFormat;
import java.util.List;

public class SaladaFrutaAdapter extends RecyclerView.Adapter  {

    private List<Produto> produtos;
    private Context context;
    private SaladaFrutaAdapter.OnSaladaDeFrutaItemClickListener listener;

    public interface OnSaladaDeFrutaItemClickListener {
        void onItemClick(Produto item);
    }

    public SaladaFrutaAdapter(List<Produto> produtos, Context context, OnSaladaDeFrutaItemClickListener listener) {
        this.produtos = produtos;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_produto_chup, viewGroup, false);

        SaladaFrutaAdapter.SaladaDeFrutasViewHolder saladaDeFrutaViewHolder = new  SaladaFrutaAdapter.SaladaDeFrutasViewHolder(view);

        return saladaDeFrutaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        SaladaFrutaAdapter.SaladaDeFrutasViewHolder holder = (SaladaFrutaAdapter.SaladaDeFrutasViewHolder) viewHolder;
        holder.bind(produtos.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return this.produtos.size();
    }

    public class SaladaDeFrutasViewHolder extends RecyclerView.ViewHolder {

        final TextView dataProduto;
        final TextView statusProduto;

        public SaladaDeFrutasViewHolder(@NonNull View itemView) {
            super(itemView);

            dataProduto = itemView.findViewById(R.id.data_produto_chup);
            statusProduto = itemView.findViewById(R.id.ativo_produto_chup);
        }

        public void bind(final Produto item, final  SaladaFrutaAdapter.OnSaladaDeFrutaItemClickListener listener) {
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);


            dataProduto.setText(simpleDateFormat.format(item.getDataBase()));
            statusProduto.setText(item.getNome());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }
}
