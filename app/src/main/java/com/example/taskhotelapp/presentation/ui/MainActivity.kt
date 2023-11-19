package com.example.taskhotelapp.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.findNavController
import com.example.taskhotelapp.R

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController: NavController = Navigation.findNavController(this,
            R.id.nav_host_fragment
        )
        appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
    }
    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()

}