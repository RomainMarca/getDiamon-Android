package fr.wildcodeschool.getdiamond;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fr.wildcodeschool.getdiamond.models.JewelryModel;


public class AdapterJewelryBuild extends RecyclerView.Adapter<AdapterJewelryBuild.MyViewHolder> {

    private List<JewelryModel> jewelryList;
    private Context ctx;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView opalInt;
        public TextView emeraldInt;
        public TextView diamondInt;
        public TextView rubyInt;
        public TextView gainInt;


        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.tv_jewelryName);
            opalInt = view.findViewById(R.id.tv_opalInt);
            emeraldInt = view.findViewById(R.id.tv_emeraldInt);
            diamondInt = view.findViewById(R.id.tv_diamondInt);
            rubyInt = view.findViewById(R.id.tv_rubyInt);
            gainInt = view.findViewById(R.id.tv_gainInt);
        }
    }


    public AdapterJewelryBuild(List<JewelryModel> jewelryList, Context ctx) {
        this.jewelryList = jewelryList;
        this.ctx = ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_jewelry_build, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final JewelryModel jewelry = jewelryList.get(position);
        holder.name.setText(jewelry.getName());
        holder.emeraldInt.setText(String.valueOf(jewelry.getEmerald()));
        holder.opalInt.setText(String.valueOf(jewelry.getOpal()));
        holder.diamondInt.setText(String.valueOf(jewelry.getDiamond()));
        holder.rubyInt.setText(String.valueOf(jewelry.getRuby()));
        holder.gainInt.setText(String.valueOf(jewelry.getGain()));


    }

    @Override
    public int getItemCount() {
        return jewelryList.size();
    }

    public void onClick(View v) {
        
    }

}