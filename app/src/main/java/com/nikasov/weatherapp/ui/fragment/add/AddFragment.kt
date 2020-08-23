package com.nikasov.weatherapp.ui.fragment.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.common.api.ApiException
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse
import com.nikasov.weatherapp.R
import com.nikasov.weatherapp.databinding.FragmentAddBinding
import com.nikasov.weatherapp.utils.TransitionUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add.*
import timber.log.Timber

@AndroidEntryPoint
class AddFragment: Fragment() {

    private val viewModel: AddViewModel by viewModels()
    private lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false)
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
        initSearch()
    }

    private fun initSearch() {
        citySearch.doAfterTextChanged {
            search(it.toString())
        }
    }

    private fun search(query: String) {

        val placesClient = Places.createClient(requireContext())
        val token = AutocompleteSessionToken.newInstance()

        val request =
            FindAutocompletePredictionsRequest.builder()
                .setTypeFilter(TypeFilter.CITIES)
                .setSessionToken(token)
                .setQuery(query)
                .build()

        placesClient.findAutocompletePredictions(request)
            .addOnSuccessListener { response: FindAutocompletePredictionsResponse ->

                val cityArray = arrayListOf<String>()

                for (prediction in response.autocompletePredictions) {
                    Timber.d(prediction.getPrimaryText(null).toString())
                    cityArray.add(prediction.getPrimaryText(null).toString())
                }

                val arrayAdapter = ArrayAdapter<String>(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    cityArray
                )

                citySearch.apply {
                    setAdapter(arrayAdapter)
                }
                citySearch.requestLayout()

            }.addOnFailureListener { exception: Exception? ->
                if (exception is ApiException) {
                    Timber.d("Place not found: %s", exception.statusCode)
                }
            }
    }
}