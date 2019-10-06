package com.example.labor1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if (getIntent().getExtras() != null) {
            ((TextView) this.findViewById(R.id.firstNameText)).setText(getIntent().getExtras().getString("firstName"));
            ((TextView) this.findViewById(R.id.lastNameText)).setText(getIntent().getExtras().getString("lastName"));
            ((TextView) this.findViewById(R.id.departmentText)).setText(getIntent().getExtras().getString("department"));
        }
    }

    public void callSignIn(View view) {
        Intent signInActivity = new Intent(this, MainActivity.class);
        startActivity(signInActivity);
    }
}
