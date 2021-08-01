package com.gketdev.gazorpazorp.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gketdev.gazorpazorp.ui.characters.CharacterViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow<Boolean>(false)
    val viewState: StateFlow<Boolean> = _state

    init {
        viewModelScope.launch {
            delay(2000)
            _state.value = true
        }
    }
}

