package com.leboncoin.assessment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.leboncoin.assessment.R


class MainActivity : AppCompatActivity() {

    internal val navigationController by lazy {
        (supportFragmentManager.findFragmentById(R.id.navigation_container) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}