package fr.wildcodeschool.getdiamond;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.wildcodeschool.getdiamond.models.UserModel;

public class LoginActivity extends AppCompatActivity {

    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final ApiSingleton apiSingleton = ApiSingleton.getInstance(this);

        Button login = findViewById(R.id.bt_login);
        final EditText name = findViewById(R.id.et_name);
        final EditText password = findViewById(R.id.et_password);

        String nameValue = name.getText().toString();
        String passwordValue = password.getText().toString();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameValue = name.getText().toString();
                String passwordValue = password.getText().toString();

                if (nameValue.isEmpty() || passwordValue.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please, complete all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    for (UserModel users: apiSingleton.getUserList()) {
                        if (users.getName().equals(nameValue) && users.getPassword().equals(passwordValue)) {
                            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                            apiSingleton.setCurrentUser(users);
                        } else {
                            Toast.makeText(LoginActivity.this, "if your first log on, create player  !", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });










    }
}
