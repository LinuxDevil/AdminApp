package com.aligmohammad.doctorappclient.ui.activities.main_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.aligmohammad.doctorappclient.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //TODO: Fix

//        val userPreferences = PreferencesStore(this)
//
//        userPreferences.accessToken.asLiveData().observe(this, Observer {
//            val activity = if (it == null) AuthActivity::class.java else HomeActivity::class.java
//            startNewActivity(activity)
//        })
        navController = Navigation.findNavController(this, R.id.fragment)
    }

    fun addActionBar() {
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

}