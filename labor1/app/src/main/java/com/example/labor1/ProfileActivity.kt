package com.example.labor1;

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class ProfileActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        var callFromLogin = 0
        @JvmStatic
        var callFromRegister = 0
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        val bundle = intent.extras
        if (bundle != null) {
            findViewById<TextView>(R.id.firstNameText).text = bundle.getString("firstName")
            findViewById<TextView>(R.id.lastNameText).text = bundle.getString("lastName")
            findViewById<TextView>(R.id.departmentText).text = bundle.getString("department")
            val caller = bundle.getString("caller")
            if (Objects.equals(caller, "login")) {
                callFromLogin++
            } else if (Objects.equals(caller, "register")) {
                callFromRegister++
            }
        }
        findViewById<TextView>(R.id.loginCallCounter).text = callFromLogin.toString()
        findViewById<TextView>(R.id.registerCallCounter).text = callFromRegister.toString()
    }

    fun callSignIn(view: View) {
        val signInActivity = Intent(this, MainActivity::class.java)
        startActivity(signInActivity)
    }
}
