package fr.wildcodeschool.getdiamond.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import fr.wildcodeschool.getdiamond.R;
import fr.wildcodeschool.getdiamond.models.ExchangeModel;


public class AdapterExchange extends RecyclerView.Adapter<AdapterExchange.MyViewHolder>
{

    private List<ExchangeModel> exchangeList;
    private Context ctx;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView nameAsker;
        public TextView nameReceiver;
        public TextView opalAsker;
        public TextView emeraldAsker;
        public TextView diamondAsker;
        public TextView rubyAsker;
        public TextView opalReceiver;
        public TextView emeraldReceiver;
        public TextView diamondReceiver;
        public TextView rubyReceiver;


        public MyViewHolder(View view) {
            super(view);
            nameAsker = view.findViewById(R.id.tv_nameAsker);
            nameReceiver = view.findViewById(R.id.tv_nameReceiver);
            opalAsker = view.findViewById(R.id.tv_opalInt);
            emeraldAsker = view.findViewById(R.id.tv_emeraldInt);
            diamondAsker = view.findViewById(R.id.tv_diamondInt);
            rubyAsker = view.findViewById(R.id.tv_rubyInt);
            opalReceiver = view.findViewById(R.id.tv_opalIntR);
            emeraldReceiver = view.findViewById(R.id.tv_emeraldIntR);
            diamondReceiver = view.findViewById(R.id.tv_diamondIntR);
            rubyReceiver = view.findViewById(R.id.tv_rubyIntR);

        }
    }


    public AdapterExchange(List<ExchangeModel> exchangeList, Context ctx) {
        this.exchangeList = exchangeList;
        this.ctx = ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exchange, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ExchangeModel exchange = exchangeList.get(position);
        holder.nameAsker.setText(exchange.getAsker().getName());
        holder.nameReceiver.setText(exchange.getReceiver().getName());

        holder.opalAsker.setText(String.valueOf(exchange.getOpalAsker()));
        holder.emeraldAsker.setText(String.valueOf(exchange.getEmeraldAsker()));
        holder.diamondAsker.setText(String.valueOf(exchange.getDiamondAsker()));
        holder.rubyAsker.setText(String.valueOf(exchange.getRubyAsker()));
        holder.opalReceiver.setText(String.valueOf(exchange.getOpalReceiver()));
        holder.emeraldReceiver.setText(String.valueOf(exchange.getEmeraldReceiver()));
        holder.diamondReceiver.setText(String.valueOf(exchange.getDiamondReceiver()));
        holder.rubyReceiver.setText(String.valueOf(exchange.getRubyReceiver()));

    }

    @Override
    public int getItemCount() {
        return exchangeList.size();
    }

    public void onClick(View v) {

    }

}
