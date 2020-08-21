package com.nikasov.weatherapp.ui.fragment.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.nikasov.weatherapp.R
import com.nikasov.weatherapp.databinding.FragmentListBinding
import com.nikasov.weatherapp.utils.TransitionUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.*

@AndroidEntryPoint
class ListFragment: Fragment() {

    private val viewModel: ListViewModel by viewModels()
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enterTransition = TransitionUtils.getTransitionBackward(false)
        returnTransition = TransitionUtils.getTransitionBackward(true)
        exitTransition = TransitionUtils.getElevationScale(true)
        reenterTransition = TransitionUtils.getElevationScale(true)
        initUi()
    }

    private fun initUi() {
        backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }

        addBtn.setOnClickListener {
            val extras = FragmentNavigatorExtras(addBtn to "shared_element_container")
            findNavController().navigate(R.id.toAddFragment, null, null, extras)
        }
    }
}