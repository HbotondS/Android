package com.example.labor1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    private static int callFromLogin = 0;
    private static int callFromRegister = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ((TextView) this.findViewById(R.id.firstNameText)).setText(bundle.getString("firstName"));
            ((TextView) this.findViewById(R.id.lastNameText)).setText(bundle.getString("lastName"));
            ((TextView) this.findViewById(R.id.departmentText)).setText(bundle.getString("department"));
            String caller = bundle.getString("caller");
            if (Objects.equals(caller, "login")) {
                callFromLogin++;
            } else if (Objects.equals(caller, "register")) {
                callFromRegister++;
            }
        }
        ((TextView) this.findViewById(R.id.loginCallCounter)).setText(String.valueOf(callFromLogin));
        ((TextView) this.findViewById(R.id.registerCallCounter)).setText(String.valueOf(callFromRegister));
    }

    public void callSignIn(View view) {
        Intent signInActivity = new Intent(this, MainActivity.class);
        startActivity(signInActivity);
    }
}
