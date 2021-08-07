package com.gketdev.gazorpazorp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.gketdev.gazorpazorp.R
import com.gketdev.gazorpazorp.base.BaseViewModelActivity
import com.gketdev.gazorpazorp.databinding.ActivityMainBinding
import com.gketdev.gazorpazorp.databinding.ActivitySplashBinding
import com.gketdev.gazorpazorp.ui.MainActivity
import com.gketdev.gazorpazorp.ui.characters.CharacterViewModel
import com.gketdev.gazorpazorp.ui.characters.CharacterViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SplashActivity :
    BaseViewModelActivity<ActivitySplashBinding, SplashViewModel>() {

    override val viewModel: SplashViewModel by viewModels()

    override fun getViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onInitView() {
    }

    override fun onInitListener() {}

    override fun onObserveData() {
        super.onObserveData()
        lifecycleScope.launchWhenCreated {
            viewModel.viewState.collect {
                if (it) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(
                        this@SplashActivity,
                        getString(R.string.text_loading),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}