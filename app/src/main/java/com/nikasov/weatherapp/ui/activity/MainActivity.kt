package com.nikasov.weatherapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.nikasov.weatherapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpUi()
    }

    private fun setUpUi() {
        setUpController()
    }

    private fun setUpController() {
        findNavController(R.id.hostFragment).addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

            }
        }
    }

}