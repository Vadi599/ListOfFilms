package com.example.listoffilms.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.listoffilms.R
import com.example.listoffilms.databinding.ActivityMainBinding
import com.example.listoffilms.fragments.splash.SplashFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, SplashFragment())
            .commit()
    }
}