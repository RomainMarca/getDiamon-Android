package fr.wildcodeschool.getdiamond;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import fr.wildcodeschool.getdiamond.models.UserModel;

public class ExchangeWithmeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_withme);
        final ApiSingleton apiSingleton = ApiSingleton.getInstance(this);


        final TextView opalAsker = findViewById(R.id.et_opal);
        final TextView emeraldAsker = findViewById(R.id.et_emerald);
        final TextView diamondAsker = findViewById(R.id.et_diamond);
        final TextView rubyAsker = findViewById(R.id.et_ruby);
        final TextView opalReceiver = findViewById(R.id.et_opalR);
        final TextView emeraldReceiver = findViewById(R.id.et_emeraldR);
        final TextView diamondReceiver = findViewById(R.id.et_diamondR);
        final TextView rubyReceiver = findViewById(R.id.et_rubyR);
        TextView nameAsk = findViewById(R.id.tv_nameAsker);
        TextView nameReceive = findViewById(R.id.tv_nameReceiver);
        Button refuse = findViewById(R.id.bt_cancel);
        Button accept = findViewById(R.id.bt_ok);
        Button close = findViewById(R.id.bt_close);

        nameAsk.setText(apiSingleton.getCurrentExchange().getAsker().getName());
        nameReceive.setText(apiSingleton.getCurrentExchange().getReceiver().getName());
        opalAsker.setText(String.valueOf(apiSingleton.getCurrentExchange().getOpalAsker()));
        emeraldAsker.setText(String.valueOf(apiSingleton.getCurrentExchange().getEmeraldAsker()));
        diamondAsker.setText(String.valueOf(apiSingleton.getCurrentExchange().getDiamondAsker()));
        rubyAsker.setText(String.valueOf(apiSingleton.getCurrentExchange().getRubyAsker()));
        opalReceiver.setText(String.valueOf(apiSingleton.getCurrentExchange().getOpalReceiver()));
        emeraldReceiver.setText(String.valueOf(apiSingleton.getCurrentExchange().getEmeraldReceiver()));
        diamondReceiver.setText(String.valueOf(apiSingleton.getCurrentExchange().getDiamondReceiver()));
        rubyReceiver.setText(String.valueOf(apiSingleton.getCurrentExchange().getRubyReceiver()));

        refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo delete Exchange
                startActivity(new Intent(ExchangeWithmeActivity.this, LoadingActivity.class));
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExchangeWithmeActivity.this, LoadingActivity.class));
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (UserModel receiver : apiSingleton.getUserList()) {
                    if (receiver.getId() == apiSingleton.getCurrentExchange().getReceiver().getId()) {
                        apiSingleton.setCurrentReceiver(receiver);
                    }
                }

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

                            int opalAV = apiSingleton.getCurrentUser().getOpal() - opalReceiverValueInt;
                            int emeraldAV = apiSingleton.getCurrentUser().getEmerald() - emeraldReceiverValueInt;
                            int diamondAV = apiSingleton.getCurrentUser().getDiamond() - diamondReceiverValueInt;
                            int rubyAV = apiSingleton.getCurrentUser().getRuby() - rubyReceiverValueInt;
                            apiSingleton.getCurrentUser().setOpal(opalAV);
                            apiSingleton.getCurrentUser().setEmerald(emeraldAV);
                            apiSingleton.getCurrentUser().setDiamond(diamondAV);
                            apiSingleton.getCurrentUser().setRuby(rubyAV);

                            int opalRV = apiSingleton.getCurrentReceiver().getOpal() - opalAskerValueInt;
                            int emeraldRV = apiSingleton.getCurrentReceiver().getEmerald() - emeraldAskerValueInt;
                            int diamondRV = apiSingleton.getCurrentReceiver().getDiamond() - diamondAskerValueInt;
                            int rubyRV = apiSingleton.getCurrentReceiver().getRuby() - rubyAskerValueInt;
                            apiSingleton.getCurrentReceiver().setOpal(opalRV);
                            apiSingleton.getCurrentReceiver().setEmerald(emeraldRV);
                            apiSingleton.getCurrentReceiver().setDiamond(diamondRV);
                            apiSingleton.getCurrentReceiver().setRuby(rubyRV);

                            apiSingleton.jsonUpdateUser(apiSingleton.getCurrentUser(), new ApiListener() {
                                @Override
                                public void onResponse(boolean success) {
                                    if (success) {
                                        apiSingleton.jsonUpdateUser(apiSingleton.getCurrentReceiver(), new ApiListener() {
                                            @Override
                                            public void onResponse(boolean success) {
                                                if (success) {
                                                    Toast.makeText(ExchangeWithmeActivity.this, "You Accept exchange !", Toast.LENGTH_SHORT).show();
                                                    //TODO delete exchange
                                                    startActivity(new Intent(ExchangeWithmeActivity.this, LoadingActivity.class));
                                                }
                                            }
                                        });
                                    }
                                }
                            });

            } else {
                    Toast.makeText(ExchangeWithmeActivity.this, "Sorry this exchange is impossible...", Toast.LENGTH_SHORT).show();
                    //Todo delete exchange
                }

            }
        });
    }
}
