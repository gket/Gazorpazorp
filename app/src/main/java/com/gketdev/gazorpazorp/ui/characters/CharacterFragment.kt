package com.gketdev.gazorpazorp.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.gketdev.gazorpazorp.adapter.CharacterPagingAdapter
import com.gketdev.gazorpazorp.base.BaseViewModelFragment
import com.gketdev.gazorpazorp.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CharacterFragment : BaseViewModelFragment<FragmentCharacterBinding, CharacterViewModel>() {

    private var characterAdapter: CharacterPagingAdapter? = null

    override val viewModel: CharacterViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentCharacterBinding {
        return FragmentCharacterBinding.inflate(inflater, container, attachToParent)
    }

    override fun onInitView() {
        characterAdapter = CharacterPagingAdapter()
        binding.recyclerViewCharacters.adapter = characterAdapter
    }

    override fun onInitListener() {
        lifecycleScope.launchWhenCreated {
            viewModel.viewState.collect {
                characterAdapter?.submitData(lifecycle, it)
            }
        }
    }

}

