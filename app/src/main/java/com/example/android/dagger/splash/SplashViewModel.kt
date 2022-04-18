package com.example.android.dagger.splash

import androidx.appcompat.app.AppCompatActivity
import com.example.android.dagger.di.ActivityScope
import com.example.android.dagger.login.LoginActivity
import com.example.android.dagger.main.MainActivity
import com.example.android.dagger.registration.RegistrationActivity
import com.example.android.dagger.user.UserManager
import javax.inject.Inject

@ActivityScope
class SplashViewModel @Inject constructor(private val userManager: UserManager) {
    fun getActivityClass(): Class<out AppCompatActivity> =
        if (!userManager.isUserLoggedIn()) {
            if (!userManager.isUserRegistered()) {
                RegistrationActivity::class.java
            } else {
                LoginActivity::class.java
            }
        } else {
            MainActivity::class.java
        }
}