package fr.wildcodeschool.getdiamond;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import fr.wildcodeschool.getdiamond.adapter.AdapterExchange;
import fr.wildcodeschool.getdiamond.adapter.AdapterRanking;
import fr.wildcodeschool.getdiamond.models.ExchangeModel;
import fr.wildcodeschool.getdiamond.models.UserModel;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        ApiSingleton apiSingleton = ApiSingleton.getInstance(this);

        //money
        RecyclerView userList = findViewById(R.id.rv_ranking_money);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(this);
        userList.setLayoutManager(LayoutManager);

        List<UserModel> user = apiSingleton.getUserList();
        ArrayList<UserModel> users = new ArrayList<>();
        //TODO ranger la list par money
        Collections.reverse(user);

        AdapterRanking adapter = new AdapterRanking(user, this);
        userList.setAdapter(adapter);

        //built
        RecyclerView userList2 = findViewById(R.id.rv_ranking_built);
        LinearLayoutManager LayoutManager2 = new LinearLayoutManager(this);
        userList.setLayoutManager(LayoutManager2);

        List<UserModel> user2 = apiSingleton.getUserList();
        Collections.reverse(user2);

        AdapterRanking adapter2 = new AdapterRanking(user2, this);
        userList2.setAdapter(adapter2);

    }
}
