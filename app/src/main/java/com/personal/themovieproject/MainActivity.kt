package com.personal.themovieproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.personal.themovieproject.view.FirstFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmenthere,
            FirstFragment()
        ).commit()
    }
}
