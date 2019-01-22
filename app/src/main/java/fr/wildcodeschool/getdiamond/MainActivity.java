package fr.wildcodeschool.getdiamond;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ApiSingleton apiSingleton = ApiSingleton.getInstance(this);

        apiSingleton.jsonCallUser(new ApiListener() {
            @Override
            public void onResponse(boolean success) {
                if (success) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                } else {
                    Toast.makeText(MainActivity.this, "error API", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
