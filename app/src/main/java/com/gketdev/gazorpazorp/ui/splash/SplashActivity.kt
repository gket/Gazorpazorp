package com.gketdev.gazorpazorp.ui.splash

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.gketdev.gazorpazorp.databinding.ActivitySplashBinding
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

        binding.button.setOnClickListener {
            viewModel.letsTryServiceCall()
        }

        lifecycleScope.launchWhenCreated {
            viewModel.viewState.collect {
                when(it){
                    is SplashViewState.Characters -> Toast.makeText(this@SplashActivity, it.characters?.get(0)?.characterName, Toast.LENGTH_SHORT)
                        .show()
                    is SplashViewState.ClearToast -> {}
                    is SplashViewState.Error -> Toast.makeText(this@SplashActivity, it.error, Toast.LENGTH_SHORT).show()
                    is SplashViewState.Loading -> Toast.makeText(this@SplashActivity, "Loading...", Toast.LENGTH_SHORT).show()
                    is SplashViewState.ShowToast -> {}
                }
            }
        }
    }
}