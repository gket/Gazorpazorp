package com.gketdev.gazorpazorp.ui.splash

import com.gketdev.gazorpazorp.data.Character

sealed class SplashViewState {
    object Loading : SplashViewState()
    data class Characters(val characters: List<Character>?) : SplashViewState()
    data class Error(val error: String?) : SplashViewState()
    data class ShowToast(val message: String) : SplashViewState()
    object ClearToast : SplashViewState()
}