package com.nikasov.weatherapp.ui.fragment.root

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.LocationServices
import com.nikasov.weatherapp.R
import com.nikasov.weatherapp.databinding.FragmentRootBinding
import com.nikasov.weatherapp.ui.adapter.DailyAdapter
import com.nikasov.weatherapp.utils.PermissionsUtil
import com.nikasov.weatherapp.utils.TransitionUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_root.*
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

@AndroidEntryPoint
class RootFragment: Fragment(),
    EasyPermissions.PermissionCallbacks{

    private val viewModel: RootViewModel by viewModels()
    private lateinit var binding: FragmentRootBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_root, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        getLocation()
        setDailyList()

        viewModel.weather.observe(viewLifecycleOwner, Observer { model ->
            binding.weather = model
//            weatherBlock.animation = AnimationUtil.fading(weatherBlock)
//            cityName.animation = AnimationUtil.fading(cityName)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            loading.isRefreshing = isLoading
        })

        menuBtn.setOnClickListener {
            reenterTransition = TransitionUtils.getTransitionBackward(true)
            exitTransition = TransitionUtils.getTransitionBackward(false)
            findNavController().navigate(R.id.toListFragment)
        }

        moreForecast.setOnClickListener {
            reenterTransition = TransitionUtils.getHold()
            exitTransition = TransitionUtils.getHold()
            val action = RootFragmentDirections.fromRootToForecastFragment(viewModel.latitude!!, viewModel.longitude!!)
            val extras = FragmentNavigatorExtras(forecastBlock to "forecastTransition")
            findNavController().navigate(action, extras)
        }

        loading.setOnRefreshListener {
            getLocation()
        }

    }

    private fun setDailyList() {
            val dailyAdapter = DailyAdapter()
        dailyRecycler.apply {
            adapter = dailyAdapter
        }
        viewModel.dailyList.observe(viewLifecycleOwner, Observer { list ->
            dailyAdapter.submitList(list)
        })
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (PermissionsUtil.hasLocationPermission(requireContext())) {
            val fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(requireActivity())

            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    location?.let {
                        val latitude =
                            location.latitude
                        val longitude =
                            location.longitude
                        viewModel.getWeatherByCoordinates(latitude.toString(), longitude.toString())
                    }
                }
        } else {
            PermissionsUtil.requestLocationPermission(this)
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else {
            PermissionsUtil.requestLocationPermission(this)
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        getLocation()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
}