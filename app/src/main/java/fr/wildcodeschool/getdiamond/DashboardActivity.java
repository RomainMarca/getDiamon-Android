package fr.wildcodeschool.getdiamond;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

        Button buildJewelry = findViewById(R.id.bt_build);
        buildJewelry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, BuildJewelryActivity.class));
            }
        });

        Button minig = findViewById(R.id.bt_mining);
        minig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //TODO CONTINUE !

                if (apiSingleton.getCurrentUser().getLastMining().after(new Date())  ) {
                    apiSingleton.getCurrentUser().setLastMining(new Date());
                    int randomNum = 1 + (int)(Math.random() * ((5 - 1) + 1));
                    int randomJewel = 1 + (int)(Math.random() * ((4 - 1) + 1));
                    //1=opal ; 2=emerald ; 3=diamond ; 4=ruby
                    if (randomJewel == 1) {
                        int opalValue = apiSingleton.getCurrentUser().getOpal() + randomNum;
                        apiSingleton.getCurrentUser().setOpal(opalValue);
                    }

                    Toast.makeText(DashboardActivity.this, String.valueOf(apiSingleton.getCurrentUser().getLastMining()), Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
