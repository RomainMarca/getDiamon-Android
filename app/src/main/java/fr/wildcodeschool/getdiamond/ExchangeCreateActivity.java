package fr.wildcodeschool.getdiamond;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import fr.wildcodeschool.getdiamond.models.ExchangeModel;

import static java.lang.Integer.parseInt;

public class ExchangeCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_create);
        final ApiSingleton apiSingleton = ApiSingleton.getInstance(this);

        TextView nameAsker = findViewById(R.id.tv_nameAsker);
        TextView nameReceiver = findViewById(R.id.tv_nameReceiver);
        final TextView opalIntAsker = findViewById(R.id.tv_opalInt);
        TextView opalIntReceiver = findViewById(R.id.tv_opalIntR);
        TextView emeraldIntAsker = findViewById(R.id.tv_emeraldInt);
        TextView emeraldIntReceiver = findViewById(R.id.tv_emeraldIntR);
        TextView diamondIntAsker = findViewById(R.id.tv_diamondInt);
        TextView diamondIntReceiver = findViewById(R.id.tv_diamondIntR);
        TextView rubyIntAsker = findViewById(R.id.tv_rubyInt);
        TextView rubyIntReceiver = findViewById(R.id.tv_rubyIntR);
        final EditText opalAsker = findViewById(R.id.et_opal);
        final EditText opalReceiver = findViewById(R.id.et_opalR);
        final EditText emeraldReceiver = findViewById(R.id.et_emeraldR);
        final EditText emeraldAsker = findViewById(R.id.et_emerald);
        final EditText diamondAsker = findViewById(R.id.et_diamond);
        final EditText diamondReceiver = findViewById(R.id.et_diamondR);
        final EditText rubyReceiver = findViewById(R.id.et_rubyR);
        final EditText rubyAsker = findViewById(R.id.et_ruby);
        Button cancel = findViewById(R.id.bt_cancel);
        Button ok = findViewById(R.id.bt_ok);

        nameAsker.setText(apiSingleton.getCurrentUser().getName());
        nameReceiver.setText(apiSingleton.currentReceiver.getName());
        opalIntAsker.setText(String.valueOf(apiSingleton.getCurrentUser().getOpal()));
        emeraldIntAsker.setText(String.valueOf(apiSingleton.getCurrentUser().getEmerald()));
        rubyIntAsker.setText(String.valueOf(apiSingleton.getCurrentUser().getRuby()));
        diamondIntAsker.setText(String.valueOf(apiSingleton.getCurrentUser().getDiamond()));
        opalIntReceiver.setText(String.valueOf(apiSingleton.getCurrentReceiver().getOpal()));
        emeraldIntReceiver.setText(String.valueOf(apiSingleton.getCurrentReceiver().getEmerald()));
        diamondIntReceiver.setText(String.valueOf(apiSingleton.getCurrentReceiver().getDiamond()));
        rubyIntReceiver.setText(String.valueOf(apiSingleton.getCurrentReceiver().getRuby()));

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExchangeCreateActivity.this, AskListActivity.class));
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String opalAskerValue = opalAsker.getText().toString();
                if (opalAskerValue.isEmpty()) { opalAskerValue = "0";}
                final int opalAskerValueInt = Integer.parseInt(opalAskerValue);
                String opalReceiverValue = opalReceiver.getText().toString();
                if (opalReceiverValue.isEmpty()) { opalReceiverValue = "0";}
                final int opalReceiverValueInt = Integer.parseInt(opalReceiverValue);

                String emeraldAskerValue = emeraldAsker.getText().toString();
                if (emeraldAskerValue.isEmpty()) { emeraldAskerValue = "0";}
                final int emeraldAskerValueInt = Integer.parseInt(emeraldAskerValue);
                String emeraldReceiverValue = emeraldReceiver.getText().toString();
                if (emeraldReceiverValue.isEmpty()) { emeraldReceiverValue = "0";}
                final int emeraldReceiverValueInt = Integer.parseInt(emeraldReceiverValue);

                String diamondAskerValue = diamondAsker.getText().toString();
                if (diamondAskerValue.isEmpty()) { diamondAskerValue = "0";}
                final int diamondAskerValueInt = Integer.parseInt(diamondAskerValue);
                String diamondReceiverValue = diamondReceiver.getText().toString();
                if (diamondReceiverValue.isEmpty()) { diamondReceiverValue = "0";}
                final int diamondReceiverValueInt = Integer.parseInt(diamondReceiverValue);

                String rubyAskerValue = rubyAsker.getText().toString();
                if (rubyAskerValue.isEmpty()) { rubyAskerValue = "0";}
                final int rubyAskerValueInt = Integer.parseInt(rubyAskerValue);
                String rubyReceiverValue = rubyReceiver.getText().toString();
                if (rubyReceiverValue.isEmpty()) { rubyReceiverValue = "0";}
                final int rubyReceiverValueInt = Integer.parseInt(rubyReceiverValue);

                if (opalAskerValueInt < apiSingleton.getCurrentUser().getOpal() &&
                       emeraldAskerValueInt < apiSingleton.getCurrentUser().getEmerald() &&
                        diamondAskerValueInt < apiSingleton.getCurrentUser().getDiamond() &&
                        rubyAskerValueInt < apiSingleton.getCurrentUser().getRuby() &&
                        opalReceiverValueInt < apiSingleton.getCurrentReceiver().getOpal() &&
                        emeraldReceiverValueInt < apiSingleton.getCurrentReceiver().getEmerald() &&
                        diamondReceiverValueInt < apiSingleton.getCurrentReceiver().getDiamond() &&
                        rubyReceiverValueInt < apiSingleton.getCurrentReceiver().getRuby()) {

                    //TODO for valid exchange
                    /*
                    int opalAV = apiSingleton.getCurrentUser().getOpal() - opalReceiverValueInt;
                    int emeraldAV = apiSingleton.getCurrentUser().getEmerald() - emeraldReceiverValueInt;
                    int diamondAV = apiSingleton.getCurrentUser().getDiamond() - diamondReceiverValueInt;
                    int rubyAV = apiSingleton.getCurrentUser().getRuby() - rubyReceiverValueInt;
                    apiSingleton.getCurrentUser().setOpal(opalAV);
                    apiSingleton.getCurrentUser().setEmerald(emeraldAV);
                    apiSingleton.getCurrentUser().setDiamond(diamondAV);
                    apiSingleton.getCurrentUser().setRuby(rubyAV);*/

                    /*
                    int opalRV = apiSingleton.getCurrentReceiver().getOpal() - opalAskerValueInt;
                    int emeraldRV = apiSingleton.getCurrentReceiver().getEmerald() - emeraldAskerValueInt;
                    int diamondRV = apiSingleton.getCurrentReceiver().getDiamond() - diamondAskerValueInt;
                    int rubyRV = apiSingleton.getCurrentReceiver().getRuby() - rubyAskerValueInt;
                    apiSingleton.getCurrentReceiver().setOpal(opalRV);
                    apiSingleton.getCurrentReceiver().setEmerald(emeraldRV);
                    apiSingleton.getCurrentReceiver().setDiamond(diamondRV);
                    apiSingleton.getCurrentReceiver().setRuby(rubyRV);*/

                    Date toDay = new Date();
                    ExchangeModel exchange = new ExchangeModel(toDay, false, apiSingleton.getCurrentUser(),
                            opalAskerValueInt, emeraldAskerValueInt, diamondAskerValueInt, rubyAskerValueInt,
                            apiSingleton.getCurrentReceiver(), opalReceiverValueInt, emeraldReceiverValueInt,
                            diamondReceiverValueInt, rubyReceiverValueInt);

                    apiSingleton.jsonAddExchange(exchange, new ApiListener() {
                        @Override
                        public void onResponse(boolean success) {
                            if (success) {
                                Toast.makeText(ExchangeCreateActivity.this, "You create new exchange", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ExchangeCreateActivity.this, DashboardActivity.class));
                            }
                        }
                    });
                }
            }
        });


    }
}
