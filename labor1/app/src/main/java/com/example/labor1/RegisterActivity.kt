package com.example.labor1;

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class RegisterActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        var callFromProfile = 0
        @JvmStatic
        var callFromLogin = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val bundle = intent.extras
        if (bundle != null) {
            val caller = bundle.getString("caller")
            if (Objects.equals(caller, "profile")) {
                callFromProfile++
            } else if (Objects.equals(caller, "login")) {
                callFromLogin++
            }
        }
        findViewById<TextView>(R.id.profileCallCounter).text = callFromProfile.toString()
        findViewById<TextView>(R.id.loginCallCounter).text = callFromLogin.toString()
    }

    fun callProfile(view: View) {
        val profileActivity = Intent(this, ProfileActivity::class.java)
                .putExtra("firstName", this.findViewById<EditText>(R.id.firstNameText).text.toString())
                .putExtra("lastName", findViewById<EditText>(R.id.lastNameText).text.toString())
                .putExtra("department", findViewById<EditText>(R.id.departmentText).text.toString())
                .putExtra("caller", "register")
        startActivity(profileActivity)
    }
}
