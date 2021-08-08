package com.gketdev.gazorpazorp.ui.characters

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.gketdev.gazorpazorp.base.BaseViewModel
import com.gketdev.gazorpazorp.data.Character
import com.gketdev.gazorpazorp.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val repository: CharacterRepository) :
    BaseViewModel() {

    private val _viewState = MutableStateFlow<PagingData<Character>>(PagingData.empty())
    val viewState: StateFlow<PagingData<Character>> = _viewState

    init {
        getAllCharacters()
    }

    fun getAllCharacters() {
        viewModelScope.launch {
            repository.getCharacters("").collect {
                Log.d("GETCHARACTERS:::", "I collected")
                _viewState.value = it
            }
        }
    }

}
