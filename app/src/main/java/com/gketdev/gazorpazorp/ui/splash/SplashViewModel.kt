package com.gketdev.gazorpazorp.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gketdev.gazorpazorp.network.NetworkState
import com.gketdev.gazorpazorp.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: CharacterRepository) :
    ViewModel() {

    private val _viewState = MutableStateFlow<SplashViewState>(SplashViewState.Loading)
    val viewState: StateFlow<SplashViewState> = _viewState

    fun letsTryServiceCall() {
        viewModelScope.launch {
            repository.getAllCharacters().collect {
                when (it) {
                    is NetworkState.Error -> _viewState.value = SplashViewState.Error(it.message)
                    is NetworkState.Loading -> _viewState.value = SplashViewState.Loading
                    is NetworkState.Success -> _viewState.value =
                        SplashViewState.Characters(it.response.results)
                }
            }
        }
    }

}
