package fr.wildcodeschool.getdiamond;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import fr.wildcodeschool.getdiamond.models.JewelryModel;

public class BuildJewelryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_jewelry);
        final ApiSingleton apiSingleton = ApiSingleton.getInstance(this);

        TextView rubyInt = findViewById(R.id.tv_rubyInt);
        final TextView emeraldInt = findViewById(R.id.tv_emeraldInt);
        TextView diamondInt = findViewById(R.id.tv_diamondInt);
        TextView opalInt = findViewById(R.id.tv_opalInt);
        TextView moneyUser = findViewById(R.id.tv_moneyUsr);

        emeraldInt.setText(String.valueOf(apiSingleton.getCurrentUser().getEmerald()));
        rubyInt.setText(String.valueOf(apiSingleton.getCurrentUser().getRuby()));
        diamondInt.setText(String.valueOf(apiSingleton.getCurrentUser().getDiamond()));
        opalInt.setText(String.valueOf(apiSingleton.getCurrentUser().getOpal()));
        moneyUser.setText(String.valueOf(apiSingleton.getCurrentUser().getMoney()));

        //RecyclerView JewelryBuild
        final RecyclerView jewelryList = findViewById(R.id.rv_jewelryList);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(this);
        jewelryList.setLayoutManager(LayoutManager);

        final List<JewelryModel> jewelry = apiSingleton.getJewelryList();

        AdapterJewelryBuild adapter = new AdapterJewelryBuild(jewelry, this);
        jewelryList.setAdapter(adapter);
        //jewelryList.setVisibility(View.VISIBLE);


        RecyclerTouchListener listener = new RecyclerTouchListener(BuildJewelryActivity.this, jewelryList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                apiSingleton.setCurrentJewel(jewelry.get(position));
                //jewelryList.setVisibility(View.INVISIBLE);

                final String name = apiSingleton.currentJewel.getName();
                final int money = apiSingleton.getCurrentJewel().getGain();

                int opalValue = apiSingleton.getCurrentUser().getOpal() - apiSingleton.getCurrentJewel().getOpal();
                int emeraldValue = apiSingleton.getCurrentUser().getEmerald() - apiSingleton.getCurrentJewel().getEmerald();
                int diamondValue = apiSingleton.getCurrentUser().getDiamond() - apiSingleton.getCurrentJewel().getDiamond();
                int rubyvalue = apiSingleton.getCurrentUser().getRuby() - apiSingleton.getCurrentJewel().getRuby();

                if (opalValue >= 0 && emeraldValue >= 0 && diamondValue >= 0 && rubyvalue >= 0) {
                    apiSingleton.getCurrentUser().setOpal(opalValue);
                    apiSingleton.getCurrentUser().setEmerald(emeraldValue);
                    apiSingleton.getCurrentUser().setDiamond(diamondValue);
                    apiSingleton.getCurrentUser().setRuby(rubyvalue);
                    int moneyValue = apiSingleton.getCurrentUser().getMoney() + money;
                    apiSingleton.getCurrentUser().setMoney(moneyValue);
                    int totalbuiltValue = apiSingleton.getCurrentUser().getTotalBuilt() + 1;
                    apiSingleton.getCurrentUser().setTotalBuilt(totalbuiltValue);

                    apiSingleton.getCurrentJewel().setBuilt(true);

                    jewelry.clear();

                    apiSingleton.jsonUpdateUser(apiSingleton.getCurrentUser(), new ApiListener() {
                        @Override
                        public void onResponse(boolean success) {
                            if (success) {

                                apiSingleton.jsonUpdateJewelry(apiSingleton.getCurrentJewel(), new ApiListener() {
                                    @Override
                                    public void onResponse(boolean success) {
                                        if (success) {
                                            startActivity(new Intent(BuildJewelryActivity.this, BuildJewelryActivity.class));


                                            apiSingleton.jsonCallJewelry(new ApiListener() {
                                                @Override
                                                public void onResponse(boolean success) {
                                                    if (success) {
                                                        startActivity(new Intent(BuildJewelryActivity.this, BuildJewelryActivity.class));
                                                        finish();
                                                    }
                                                }
                                            });


                                        } else {
                                            Toast.makeText(BuildJewelryActivity.this, "API Error Update Jewel", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(BuildJewelryActivity.this, DashboardActivity.class));
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(BuildJewelryActivity.this, "Sorry, You can't built this jewel...", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(BuildJewelryActivity.this, BuildJewelryActivity.class));

                            }
                        }
                    });
                } else {
                    Toast.makeText(BuildJewelryActivity.this, "API Error Update User", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuildJewelryActivity.this, DashboardActivity.class));

                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
        jewelryList.addOnItemTouchListener(listener);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(BuildJewelryActivity.this, DashboardActivity.class));
    }
}
