package com.gketdev.gazorpazorp.ui.characters

import com.gketdev.gazorpazorp.data.Character

sealed class CharacterViewState {
    object Loading : CharacterViewState()
    data class Characters(val characters: List<Character>?) : CharacterViewState()
    data class Error(val error: String?) : CharacterViewState()
    data class ShowToast(val message: String) : CharacterViewState()
    object ClearToast : CharacterViewState()
}