package com.example.labor1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class registerActivity extends AppCompatActivity {

    private static int callFromProfile = 0;
    private static int callFromLogin = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String caller = bundle.getString("caller");
            if (Objects.equals(caller, "profile")) {
                callFromProfile++;
            } else if (Objects.equals(caller, "login")) {
                callFromLogin++;
            }
        }
        ((TextView) this.findViewById(R.id.profileCallCounter)).setText(String.valueOf(callFromProfile));
        ((TextView) this.findViewById(R.id.loginCallCounter)).setText(String.valueOf(callFromLogin));
    }

    public void callProfile(View view) {
        Intent profileActivity = new Intent(this, ProfileActivity.class)
                .putExtra("firstName", ((EditText) this.findViewById(R.id.firstNameText)).getText().toString())
                .putExtra("lastName", ((EditText) this.findViewById(R.id.lastNameText)).getText().toString())
                .putExtra("department", ((EditText) this.findViewById(R.id.departmentText)).getText().toString())
                .putExtra("caller", "register");
        startActivity(profileActivity);
    }
}
