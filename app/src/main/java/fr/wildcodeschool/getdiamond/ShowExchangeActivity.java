package fr.wildcodeschool.getdiamond;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fr.wildcodeschool.getdiamond.adapter.AdapterExchange;
import fr.wildcodeschool.getdiamond.adapter.AdapterJewelryBuild;
import fr.wildcodeschool.getdiamond.models.ExchangeModel;
import fr.wildcodeschool.getdiamond.models.JewelryModel;

public class ShowExchangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_exchange);
        ApiSingleton apiSingleton = ApiSingleton.getInstance(this);

        //my exchange
        RecyclerView exchangeList = findViewById(R.id.rv_ask_exchange);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(this);
        exchangeList.setLayoutManager(LayoutManager);

        List<ExchangeModel> exchange = apiSingleton.getExchangeList();
        ArrayList<ExchangeModel> myExchange = new ArrayList<>();
        for (ExchangeModel exchanges: exchange) {
            if (exchanges.getAsker().getId() == apiSingleton.getCurrentUser().getId()) {
                myExchange.add(exchanges);
            }
        }
        AdapterExchange adapter = new AdapterExchange(myExchange, this);
        exchangeList.setAdapter(adapter);

        RecyclerTouchListener listener = new RecyclerTouchListener(ShowExchangeActivity.this, exchangeList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //TODO faire unne nouvelle activity pour annulé
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
        exchangeList.addOnItemTouchListener(listener);


        //exchange with me
        RecyclerView exchangeList2 = findViewById(R.id.rv_receive_exchange);
        LinearLayoutManager LayoutManager2 = new LinearLayoutManager(this);
        exchangeList2.setLayoutManager(LayoutManager2);

        ArrayList<ExchangeModel> yourExchange = new ArrayList<>();
        for (ExchangeModel exchanges: exchange) {
            if (exchanges.getReceiver().getId() == apiSingleton.getCurrentUser().getId()) {
                yourExchange.add(exchanges);
            }
        }
        AdapterExchange adapter2 = new AdapterExchange(yourExchange, this);
        exchangeList2.setAdapter(adapter2);

        RecyclerTouchListener listener2 = new RecyclerTouchListener(ShowExchangeActivity.this, exchangeList2, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //TODO faire unne nouvelle activity pour choisir oui ou non;
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
        exchangeList.addOnItemTouchListener(listener2);
    }
}
