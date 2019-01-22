package fr.wildcodeschool.getdiamond;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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

    }
}
