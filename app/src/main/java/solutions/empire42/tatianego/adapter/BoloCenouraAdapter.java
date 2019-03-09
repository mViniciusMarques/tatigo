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

public class BoloCenouraAdapter extends RecyclerView.Adapter {

    private List<Produto> produtos;
    private Context context;
    private BoloCenouraAdapter.OnBoloCenouraItemClickListener listener;

    public interface OnBoloCenouraItemClickListener {
        void onItemClick(Produto item);
    }


    public BoloCenouraAdapter(List<Produto> produtos, Context context, BoloCenouraAdapter.OnBoloCenouraItemClickListener listener) {
        this.produtos = produtos;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_produto, viewGroup, false);

        BoloCenouraAdapter.BoloCenouraViewHolder boloCenouraViewHolder = new  BoloCenouraAdapter.BoloCenouraViewHolder(view);

         return boloCenouraViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
         BoloCenouraAdapter.BoloCenouraViewHolder holder = (BoloCenouraAdapter.BoloCenouraViewHolder) viewHolder;
         holder.bind(produtos.get(i),listener);
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public class BoloCenouraViewHolder extends RecyclerView.ViewHolder {

        final TextView dataProduto;
        final TextView statusProduto;

        //final Switch ativoEncomenda;

        public BoloCenouraViewHolder(@NonNull View itemView) {
            super(itemView);

            dataProduto = itemView.findViewById(R.id.data_produto);
            statusProduto = itemView.findViewById(R.id.ativo_produto);
        }

        public void bind(final Produto item, final OnBoloCenouraItemClickListener listener) {
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
