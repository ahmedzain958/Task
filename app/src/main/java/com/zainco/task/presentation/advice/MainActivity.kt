package com.zainco.task.presentation.advice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zainco.task.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            navigateToGalleryPage()
        }
    }
    private fun navigateToGalleryPage() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.advice_container,
                AdviceFragment.newInstance(),
                AdviceFragment.FRAGMENT_NAME
            ).commitAllowingStateLoss()
    }

}
