package com.gketdev.gazorpazorp.ui.characterdetail

import com.gketdev.gazorpazorp.data.Character

sealed class CharacterDetailState {
    object Loading : CharacterDetailState()
    data class CharacterInfo(val character: Character) : CharacterDetailState()
    data class Error(val error: String?) : CharacterDetailState()
}