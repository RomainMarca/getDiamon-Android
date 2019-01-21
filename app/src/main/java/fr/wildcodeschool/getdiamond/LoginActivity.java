package fr.wildcodeschool.getdiamond;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final ApiSingleton apiSingleton = ApiSingleton.getInstance(this);

        TextView test = findViewById(R.id.tv_test);

        for (UserModel users: apiSingleton.getUserList()) {
            user = users.getName();
        }
        test.setText(user);
    }
}
