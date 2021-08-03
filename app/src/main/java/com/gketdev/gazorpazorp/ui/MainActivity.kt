package com.gketdev.gazorpazorp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.gketdev.gazorpazorp.R
import com.gketdev.gazorpazorp.base.BaseActivity
import com.gketdev.gazorpazorp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var navHostFragment: NavHostFragment

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onInitView() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController
        navController.let { binding.bottomNavigationView.setupWithNavController(it) }
    }

    override fun onSupportNavigateUp() = NavHostFragment.findNavController(navHostFragment).navigateUp()

    override fun onInitListener() {

    }




}