package com.mogsev.testapplication.gui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.mogsev.testapplication.gui.activities.login.LoginActivity

/**
 * Created by Eugene Sikaylo
 * email: mogsev@gmail.com
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
        }, 1000)
    }

}
