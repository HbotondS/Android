package com.example.labor1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    // TODO: implement onCallBack

    private static int callFromProfile = 0;
    private static int callFromRegister = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String caller = bundle.getString("caller");
            if (Objects.equals(caller, "profile")) {
                callFromProfile++;
            } else if (Objects.equals(caller, "register")) {
                callFromRegister++;
            }
        }
        ((TextView) this.findViewById(R.id.profileCallCounter)).setText(String.valueOf(callFromProfile));
        ((TextView) this.findViewById(R.id.registerCallCounter)).setText(String.valueOf(callFromRegister));
    }

    public void callRegister(View view) {
        Intent registerActivity = new Intent(this, registerActivity.class)
                .putExtra("caller", "login");
        startActivity(registerActivity);
    }

    public void callProfile(View view) {
        Intent profileActivity = new Intent(this, ProfileActivity.class)
                .putExtra("firstName", ((EditText) this.findViewById(R.id.firstNameText)).getText().toString())
                .putExtra("lastName", ((EditText) this.findViewById(R.id.lastNameText)).getText().toString())
                .putExtra("caller", "login");
        startActivity(profileActivity);
    }
}
