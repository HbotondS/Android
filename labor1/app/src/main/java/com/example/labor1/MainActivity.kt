package com.example.labor1;

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        var callFromProfile = 0
        @JvmStatic
        var callFromRegister = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle = intent.extras
        if (bundle != null) {
            val caller = bundle.getString("caller")
            if (Objects.equals(caller, "profile")) {
                callFromProfile++
            } else if (Objects.equals(caller, "register")) {
                callFromRegister++
            }
        }
        findViewById<TextView>(R.id.profileCallCounter).text = callFromProfile.toString()
        findViewById<TextView>(R.id.registerCallCounter).text = callFromRegister.toString()
    }

    fun callRegister(view: View) {
        val registerActivity = Intent(this, RegisterActivity::class.java)
                .putExtra("caller", "login")
        startActivity(registerActivity)
    }

    fun callProfile(view: View) {
        val profileActivity = Intent(this, ProfileActivity::class.java)
                .putExtra("firstName", findViewById<EditText>(R.id.firstNameText).text.toString())
                .putExtra("lastName", findViewById<EditText>(R.id.lastNameText).text.toString())
                .putExtra("caller", "login");
        startActivity(profileActivity);
    }
}
