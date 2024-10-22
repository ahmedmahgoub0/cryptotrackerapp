package com.acoding.cryptotrackerapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.acoding.cryptotrackerapp.core.presentation.util.ObserveAsEvents
import com.acoding.cryptotrackerapp.core.presentation.util.toString
import com.acoding.cryptotrackerapp.cryptotracker.presentaion.coin_list.CoinListEvent
import com.acoding.cryptotrackerapp.cryptotracker.presentaion.coin_list.CoinListScreen
import com.acoding.cryptotrackerapp.cryptotracker.presentaion.coin_list.CoinListViewModel
import com.acoding.cryptotrackerapp.ui.theme.CryptoTrackerTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val context = LocalContext.current
                    val viewModel = koinViewModel<CoinListViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    ObserveAsEvents(event = viewModel.event) {
                        when (it) {
                            is CoinListEvent.Error -> {
                                Toast.makeText(
                                    context,
                                    it.error.toString(context),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                    CoinListScreen(
                        state = state,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}