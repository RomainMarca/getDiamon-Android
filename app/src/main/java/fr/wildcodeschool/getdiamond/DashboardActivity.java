package fr.wildcodeschool.getdiamond;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        final ApiSingleton apiSingleton = ApiSingleton.getInstance(this);


        TextView nameUser = findViewById(R.id.tv_nameUser);
        TextView rubyInt = findViewById(R.id.tv_rubyInt);
        TextView emeraldInt = findViewById(R.id.tv_emeraldInt);
        TextView diamondInt = findViewById(R.id.tv_diamondInt);
        TextView opalInt = findViewById(R.id.tv_opalInt);
        TextView moneyUser = findViewById(R.id.tv_money);

        nameUser.setText(apiSingleton.getCurrentUser().getName());
        emeraldInt.setText(String.valueOf(apiSingleton.getCurrentUser().getEmerald()));
        rubyInt.setText(String.valueOf(apiSingleton.getCurrentUser().getRuby()));
        diamondInt.setText(String.valueOf(apiSingleton.getCurrentUser().getDiamond()));
        opalInt.setText(String.valueOf(apiSingleton.getCurrentUser().getOpal()));
        moneyUser.setText(String.valueOf(apiSingleton.getCurrentUser().getMoney()));

        final Button minig = findViewById(R.id.bt_mining);
        minig.setVisibility(View.VISIBLE);

        ImageView ranking = findViewById(R.id.iv_ranking);
        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, RankingActivity.class));
            }
        });

        Button showExchange = findViewById(R.id.bt_exchange);
        showExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, LoadingActivity.class));
            }
        });

        Button buildJewelry = findViewById(R.id.bt_build);
        buildJewelry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, BuildJewelryActivity.class));
            }
        });

        Button askExchange = findViewById(R.id.bt_ask);
        askExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, AskListActivity.class));
            }
        });


        minig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                minig.setVisibility(View.INVISIBLE);

               SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
               Date lastMining = apiSingleton.getCurrentUser().getLastMining();
               String lastMiningValue = String.valueOf(lastMining);

                if (lastMining == null) {
                    lastMiningValue = "01-01-2019";
                }

               Date toDay = new Date();
               String toDayValue = String.valueOf(toDay);

               try {
                   lastMining = sdf.parse(lastMiningValue);
                   toDay = sdf.parse(toDayValue);

               } catch (ParseException e) {
                   e.printStackTrace();
               }

                if (toDay.getDay() !=lastMining.getDay() || toDay.getMonth() !=lastMining.getMonth()
                        || toDay.getYear() !=lastMining.getYear())  {
                    apiSingleton.getCurrentUser().setLastMining(toDay);
                    //Toast.makeText(DashboardActivity.this, "OK", Toast.LENGTH_SHORT).show();

                    final int randomNum = 1 + (int)(Math.random() * ((5 - 1) + 1));
                    int randomJewel = 1 + (int)(Math.random() * ((4 - 1) + 1));
                    String str = "";

                    //1=opal ; 2=emerald ; 3=diamond ; 4=ruby
                    if (randomJewel == 1) {
                        str = "Opal";
                        int opalValue = apiSingleton.getCurrentUser().getOpal() + randomNum;
                        apiSingleton.getCurrentUser().setOpal(opalValue);
                    }
                    if (randomJewel == 2) {
                        str = "Emerald";
                        int emeraldValue = apiSingleton.getCurrentUser().getEmerald() + randomNum;
                        apiSingleton.getCurrentUser().setEmerald(emeraldValue);
                    }
                    if (randomJewel == 3) {
                        str = "Diamond";
                        int diamondValue = apiSingleton.getCurrentUser().getDiamond() + randomNum;
                        apiSingleton.getCurrentUser().setDiamond(diamondValue);
                    }
                    if (randomJewel == 4) {
                        str = "Ruby";
                        int rubyValue = apiSingleton.getCurrentUser().getRuby() + randomNum;
                        apiSingleton.getCurrentUser().setRuby(rubyValue);
                    }

                    final String finalStr = str;
                    apiSingleton.jsonUpdateUser(apiSingleton.getCurrentUser(), new ApiListener() {
                        @Override
                        public void onResponse(boolean success) {
                            if (success) {
                                Toast.makeText(DashboardActivity.this, "You mined" + String.valueOf(randomNum) + " + " + finalStr, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(DashboardActivity.this, DashboardActivity.class));
                            } else {
                                Toast.makeText(DashboardActivity.this, "ErrorAPI", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(DashboardActivity.this, DashboardActivity.class));
                            }
                        }
                    });

                } else {
                    Toast.makeText(DashboardActivity.this, "Sorry you have mined to day", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DashboardActivity.this, DashboardActivity.class));
                }
            }
        });
    }

    @Override
    public void onBackPressed() {}
}
