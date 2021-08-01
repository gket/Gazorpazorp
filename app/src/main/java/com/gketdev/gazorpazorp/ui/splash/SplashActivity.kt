package com.gketdev.gazorpazorp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.gketdev.gazorpazorp.R
import com.gketdev.gazorpazorp.databinding.ActivitySplashBinding
import com.gketdev.gazorpazorp.ui.MainActivity
import com.gketdev.gazorpazorp.ui.characters.CharacterViewModel
import com.gketdev.gazorpazorp.ui.characters.CharacterViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        lifecycleScope.launchWhenCreated {
            viewModel.viewState.collect {
                if (it) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
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