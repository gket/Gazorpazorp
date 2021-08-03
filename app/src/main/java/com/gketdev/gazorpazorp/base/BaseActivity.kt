package com.gketdev.gazorpazorp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    lateinit var binding: VB

    abstract fun getViewBinding(): VB

    open fun onObserveData() {}

    abstract fun onInitView()

    abstract fun onInitListener()

    open fun onPreInit(savedInstanceState: Bundle?) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        onPreInit(savedInstanceState)
        onObserveData()
        onInitView()
        onInitListener()
    }




}