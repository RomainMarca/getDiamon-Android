package fr.wildcodeschool.getdiamond;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fr.wildcodeschool.getdiamond.adapter.AdapterUserAsk;
import fr.wildcodeschool.getdiamond.models.UserModel;

public class AskListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_list);
        final ApiSingleton apiSingleton = ApiSingleton.getInstance(this);

        //RecyclerView userAsk
        final RecyclerView userList = findViewById(R.id.rv_userAsk);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(this);
        userList.setLayoutManager(LayoutManager);

        final List<UserModel> users = apiSingleton.getUserList();
        final List<UserModel> userAsk = new ArrayList<>();
        for (UserModel user : users) {
            if (user.getId() != apiSingleton.getCurrentUser().getId()) {
                userAsk.add(user);
            }
        }

        AdapterUserAsk adapter = new AdapterUserAsk(userAsk, this);
        userList.setAdapter(adapter);

        RecyclerTouchListener listener = new RecyclerTouchListener(AskListActivity.this, userList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                apiSingleton.setCurrentReceiver(userAsk.get(position));
                startActivity(new Intent(AskListActivity.this, ExchangeCreateActivity.class));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
        userList.addOnItemTouchListener(listener);
    }
}
