package com.example.android.dagger.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.android.dagger.MyApplication
import com.example.android.dagger.R
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as MyApplication).appComponent.splashComponent().create().inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        showAnimationAndNavigate()
    }

    private fun showAnimationAndNavigate() {
        val icon = findViewById<ImageView>(R.id.icon)
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_slide)


        slideAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                showActivity()
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

        })
        icon.startAnimation(slideAnimation)
    }

    private fun showActivity() {
        val activity = splashViewModel.getActivityClass()
        startActivity(Intent(this, activity))
        finish()
    }
}