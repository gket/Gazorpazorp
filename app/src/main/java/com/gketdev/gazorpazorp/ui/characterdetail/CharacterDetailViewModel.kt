package com.gketdev.gazorpazorp.ui.characterdetail

import androidx.lifecycle.viewModelScope
import com.gketdev.gazorpazorp.base.BaseViewModel
import com.gketdev.gazorpazorp.network.NetworkState
import com.gketdev.gazorpazorp.repository.CharacterDetailRepository
import com.gketdev.gazorpazorp.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val repository: CharacterDetailRepository) :
    BaseViewModel() {


    private val _viewState = MutableStateFlow<CharacterDetailState>(CharacterDetailState.Loading)
    val viewState: StateFlow<CharacterDetailState> = _viewState

    @InternalCoroutinesApi
    fun getSingleCharacterById(id: Int) {
        viewModelScope.launch {
            repository.getSingleCharWithId(id).collect {
                when (it) {
                    is NetworkState.Success -> {
                        _viewState.value = CharacterDetailState.CharacterInfo(it.response)
                    }
                    is NetworkState.Error -> {
                        _viewState.value = CharacterDetailState.Error(it.message)
                    }
                    is NetworkState.Loading -> {
                        _viewState.value = CharacterDetailState.Loading
                    }
                }
            }
        }
    }
}