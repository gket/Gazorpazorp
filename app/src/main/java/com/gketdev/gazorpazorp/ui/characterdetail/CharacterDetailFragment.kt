package com.gketdev.gazorpazorp.ui.characterdetail

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.gketdev.gazorpazorp.adapter.CharacterPagingAdapter
import com.gketdev.gazorpazorp.base.BaseViewModelFragment
import com.gketdev.gazorpazorp.data.Character
import com.gketdev.gazorpazorp.databinding.FragmentCharacterBinding
import com.gketdev.gazorpazorp.databinding.FragmentCharacterDetailBinding
import com.gketdev.gazorpazorp.ui.characters.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@InternalCoroutinesApi
@AndroidEntryPoint
class CharacterDetailFragment :
    BaseViewModelFragment<FragmentCharacterDetailBinding, CharacterDetailViewModel>() {

    override val viewModel: CharacterDetailViewModel by viewModels()
    private val args by navArgs<CharacterDetailFragmentArgs>()
    private var characterId: Int = 0

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentCharacterDetailBinding {
        return FragmentCharacterDetailBinding.inflate(inflater, container, attachToParent)
    }

    override fun onInitView() {
        characterId = args.id
        viewModel.getSingleCharacterById(characterId)
    }

    override fun onInitListener() {
        lifecycleScope.launchWhenCreated {
            viewModel.viewState.collect {
                when (it) {
                    is CharacterDetailState.CharacterInfo -> setUi(it.character)
                    is CharacterDetailState.Error -> Log.d("CHARINFO:", it.error.toString())
                    CharacterDetailState.Loading -> Log.d("CHARINFO:", "Loading")
                }
            }
        }
    }

    private fun setUi(character: Character) {
        binding.textViewCharacter.text = character.characterName
        Glide.with(this).load(character.image).into(binding.imageViewCharacter)
        binding.textViewStatus.text = character.status
    }

}

