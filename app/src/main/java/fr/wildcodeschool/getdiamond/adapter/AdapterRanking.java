package fr.wildcodeschool.getdiamond.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fr.wildcodeschool.getdiamond.R;
import fr.wildcodeschool.getdiamond.models.ExchangeModel;
import fr.wildcodeschool.getdiamond.models.UserModel;

public class AdapterRanking extends RecyclerView.Adapter<AdapterRanking.MyViewHolder> {

    private List<UserModel> rankingList;
    private Context ctx;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView money;
        public TextView built;


        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.tv_nameE);
            money = view.findViewById(R.id.tv_moneyInt);
            built = view.findViewById(R.id.tv_builtInt);
        }
    }


    public AdapterRanking(List<UserModel> rankingList, Context ctx) {
        this.rankingList = rankingList;
        this.ctx = ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ranking, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final UserModel users = rankingList.get(position);
        holder.name.setText(users.getName());
        holder.money.setText(String.valueOf(users.getMoney()));
        holder.built.setText(String.valueOf(users.getTotalBuilt()));
    }

    @Override
    public int getItemCount() {
        return rankingList.size();
    }

    public void onClick(View v) {

    }
}