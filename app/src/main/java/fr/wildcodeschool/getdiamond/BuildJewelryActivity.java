package fr.wildcodeschool.getdiamond;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.wildcodeschool.getdiamond.models.JewelryModel;

public class BuildJewelryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_jewelry);
        final ApiSingleton apiSingleton = ApiSingleton.getInstance(this);


        TextView rubyInt = findViewById(R.id.tv_rubyInt);
        TextView emeraldInt = findViewById(R.id.tv_emeraldInt);
        TextView diamondInt = findViewById(R.id.tv_diamondInt);
        TextView opalInt = findViewById(R.id.tv_opalInt);
        TextView nameUser = findViewById(R.id.tv_nameUser);

        emeraldInt.setText(String.valueOf(apiSingleton.getCurrentUser().getEmerald()));
        rubyInt.setText(String.valueOf(apiSingleton.getCurrentUser().getRuby()));
        diamondInt.setText(String.valueOf(apiSingleton.getCurrentUser().getDiamond()));
        opalInt.setText(String.valueOf(apiSingleton.getCurrentUser().getOpal()));
        nameUser.setText(apiSingleton.getCurrentUser().getName());

        //RecyclerView JewelryBuild
        RecyclerView jewelryList = findViewById(R.id.rv_jewelryList);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(this);
        jewelryList.setLayoutManager(LayoutManager);

        final List<JewelryModel> jewelry = apiSingleton.getJewelryList();

        AdapterJewelryBuild adapter = new AdapterJewelryBuild(jewelry, this);
        jewelryList.setAdapter(adapter);

        RecyclerTouchListener listener = new RecyclerTouchListener(BuildJewelryActivity.this, jewelryList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                apiSingleton.setCurrentJewel(jewelry.get(position));
                Toast.makeText(BuildJewelryActivity.this, apiSingleton.getCurrentJewel().getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
        jewelryList.addOnItemTouchListener(listener);
    }
}
