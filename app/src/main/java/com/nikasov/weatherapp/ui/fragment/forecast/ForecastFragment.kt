package com.nikasov.weatherapp.ui.fragment.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.nikasov.weatherapp.R
import com.nikasov.weatherapp.databinding.FragmentForecastBinding
import com.nikasov.weatherapp.ui.adapter.ForecastAdapter
import com.nikasov.weatherapp.utils.TransitionUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_forecast.*
import timber.log.Timber

@AndroidEntryPoint
class ForecastFragment: Fragment() {

    private val args: ForecastFragmentArgs by navArgs()

    private val viewModel: ForecastViewModel by viewModels()
    private lateinit var binding: FragmentForecastBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_forecast, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedElementEnterTransition = TransitionUtils.getContainerTransform()
        initUi()
    }

    private fun initUi() {
        initList()

        backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initList() {

        viewModel.getForecastList(args.cityId)

        val forecastAdapter = ForecastAdapter()
        forecastRecycler.apply {
            adapter = forecastAdapter
        }

        viewModel.forecastList.observe(viewLifecycleOwner, Observer { list ->
            forecastAdapter.submitList(list)
        })
    }
}