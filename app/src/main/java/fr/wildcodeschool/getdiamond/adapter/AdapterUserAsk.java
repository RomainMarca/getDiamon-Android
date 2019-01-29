package fr.wildcodeschool.getdiamond.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fr.wildcodeschool.getdiamond.R;
import fr.wildcodeschool.getdiamond.models.UserModel;

public class AdapterUserAsk extends RecyclerView.Adapter<AdapterUserAsk.MyViewHolder>
{

    private List<UserModel> userList;
    private Context ctx;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView opalInt;
        public TextView emeraldInt;
        public TextView diamondInt;
        public TextView rubyInt;


        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.tv_nameUser);
            opalInt = view.findViewById(R.id.tv_opalInt);
            emeraldInt = view.findViewById(R.id.tv_emeraldInt);
            diamondInt = view.findViewById(R.id.tv_diamondInt);
            rubyInt = view.findViewById(R.id.tv_rubyInt);
        }
    }


    public AdapterUserAsk(List<UserModel> userList, Context ctx) {
        this.userList = userList;
        this.ctx = ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_ask, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final UserModel users = userList.get(position);
        holder.name.setText(users.getName());
        holder.emeraldInt.setText(String.valueOf(users.getEmerald()));
        holder.opalInt.setText(String.valueOf(users.getOpal()));
        holder.diamondInt.setText(String.valueOf(users.getDiamond()));
        holder.rubyInt.setText(String.valueOf(users.getRuby()));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void onClick(View v) {

    }

}