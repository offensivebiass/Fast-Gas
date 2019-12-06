package com.sellcom.fastgas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.core.app.ActivityCompat.requestPermissions



class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        requestPermissions(
            this,
            arrayOf(
                ACCESS_FINE_LOCATION,ACCESS_COARSE_LOCATION
            ),
            1001
        )

        login_btn.setOnClickListener {
            val i = Intent(this, MapsActivity::class.java)
            startActivity(i)
        }
    }
}
