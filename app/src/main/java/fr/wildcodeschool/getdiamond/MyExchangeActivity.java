package fr.wildcodeschool.getdiamond;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyExchangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_exchange);
        ApiSingleton apiSingleton = ApiSingleton.getInstance(this);

        TextView opalAsk = findViewById(R.id.et_opal);
        TextView emeraldAsk = findViewById(R.id.et_emerald);
        TextView diamondAsk = findViewById(R.id.et_diamond);
        TextView rubyAsk = findViewById(R.id.et_ruby);
        TextView opalReceive = findViewById(R.id.et_opalR);
        TextView emeraldReceive = findViewById(R.id.et_emeraldR);
        TextView diamondReceive = findViewById(R.id.et_diamondR);
        TextView rubyReceive = findViewById(R.id.et_rubyR);
        TextView nameAsk = findViewById(R.id.tv_nameAsker);
        TextView nameReceive = findViewById(R.id.tv_nameReceiver);
        Button cancel = findViewById(R.id.bt_cancel);
        Button remove = findViewById(R.id.bt_ok);

        nameAsk.setText(apiSingleton.getCurrentExchange().getAsker().getName());
        nameReceive.setText(apiSingleton.getCurrentExchange().getReceiver().getName());
        opalAsk.setText(String.valueOf(apiSingleton.getCurrentExchange().getOpalAsker()));
        emeraldAsk.setText(String.valueOf(apiSingleton.getCurrentExchange().getEmeraldAsker()));
        diamondAsk.setText(String.valueOf(apiSingleton.getCurrentExchange().getDiamondAsker()));
        rubyAsk.setText(String.valueOf(apiSingleton.getCurrentExchange().getRubyAsker()));
        opalReceive.setText(String.valueOf(apiSingleton.getCurrentExchange().getOpalReceiver()));
        emeraldReceive.setText(String.valueOf(apiSingleton.getCurrentExchange().getEmeraldReceiver()));
        diamondReceive.setText(String.valueOf(apiSingleton.getCurrentExchange().getDiamondReceiver()));
        rubyReceive.setText(String.valueOf(apiSingleton.getCurrentExchange().getRubyReceiver()));

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyExchangeActivity.this, DashboardActivity.class));
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo delete exchange
                startActivity(new Intent(MyExchangeActivity.this, LoadingActivity.class));

            }
        });
    }
}
