package com.example.labor1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class registerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void callProfile(View view) {
        Intent profileActivity = new Intent(this, ProfileActivity.class)
                .putExtra("firstName", ((EditText) this.findViewById(R.id.firstNameText)).getText().toString())
                .putExtra("lastName", ((EditText) this.findViewById(R.id.lastNameText)).getText().toString())
                .putExtra("department", ((EditText) this.findViewById(R.id.departmentText)).getText().toString());
        startActivity(profileActivity);
    }
}
