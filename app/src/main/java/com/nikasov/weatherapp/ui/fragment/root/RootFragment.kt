package com.nikasov.weatherapp.ui.fragment.root

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.transition.MaterialFadeThrough
import com.nikasov.weatherapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootFragment: Fragment (R.layout.fragment_root) {

    private val viewModel: RootViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enterTransition = MaterialFadeThrough()
        initUi()
    }

    private fun initUi() {

    }

}