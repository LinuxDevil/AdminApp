package com.aligmohammad.doctorappclient.ui.activities.splash_activity

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.asLiveData
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.helpers.PreferencesStore
import com.aligmohammad.doctorappclient.ui.activities.main_activity.MainActivity
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.util.*

class SplashActivity : AppCompatActivity() {

    private lateinit var userPreferencesStore: PreferencesStore

    private fun saveLanguage(lang: String) = GlobalScope.async {
        userPreferencesStore.saveLanguage(lang)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        logoSplash.animate().alpha(0f).setDuration(3000L).start()

        userPreferencesStore = PreferencesStore(this)
        userPreferencesStore.lanuage.asLiveData().observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                saveLanguage(it)
                when (it) {
                    "en" -> {
                        val locale = Locale("en")
                        Locale.setDefault(locale)
                        val config: Configuration = baseContext.resources.configuration
                        config.locale = locale
                        baseContext.resources.updateConfiguration(
                            config,
                            baseContext.resources.displayMetrics
                        )
                    }
                    "ar" -> {
                        val locale = Locale("ar")
                        Locale.setDefault(locale)
                        val config: Configuration = baseContext.resources.configuration
                        config.locale = locale
                        baseContext.resources.updateConfiguration(
                            config,
                            baseContext.resources.displayMetrics
                        )
                    }
                }
            } else {
                val locale = Locale("en")
                Locale.setDefault(locale)
                saveLanguage("en")
                val config: Configuration = baseContext.resources.configuration
                config.locale = locale
                baseContext.resources.updateConfiguration(
                    config,
                    baseContext.resources.displayMetrics
                )
            }
        })

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val TAG = "Google Services"
        if (checkGooglePlayServices()) {

            FirebaseApp.initializeApp(applicationContext)
            MobileAds.initialize(this) {}
            FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w(TAG, "getInstanceId failed", task.exception)
                        return@OnCompleteListener
                    }

                    // Get new Instance ID token
                    val token = task.result?.token

                    Log.d(TAG, token)
//                    Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
                })
            FirebaseApp.initializeApp(applicationContext)
            MobileAds.initialize(this) {}
            Firebase.messaging.isAutoInitEnabled = true

            val countDownTimer = object : CountDownTimer(3000L, 1000L) {
                override fun onTick(p0: Long) {
                }

                override fun onFinish() {
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    intent.addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                                Intent.FLAG_ACTIVITY_NEW_TASK
                    )
                    startActivity(intent)
                }
            }.start()

        } else {
            Toast.makeText(applicationContext, "Please enable Google Services", Toast.LENGTH_LONG)
                .show()
        }

    }

    private fun checkGooglePlayServices(): Boolean {
        val TAG = "Google Services"
        // 1
        val status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)
        // 2
        return if (status != ConnectionResult.SUCCESS) {
            Log.e(TAG, "Error")
            // ask user to update google play services and manage the error.
            false
        } else {
            // 3
            Log.i(TAG, "Google play services updated")
            true
        }
    }

}