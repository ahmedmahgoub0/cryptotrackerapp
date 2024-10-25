@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.acoding.cryptotrackerapp.core.navigation

import android.widget.Toast
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.acoding.cryptotrackerapp.core.presentation.util.ObserveAsEvents
import com.acoding.cryptotrackerapp.core.presentation.util.toString
import com.acoding.cryptotrackerapp.cryptotracker.presentaion.coin_details.CoinDetailsScreen
import com.acoding.cryptotrackerapp.cryptotracker.presentaion.coin_list.CoinListAction
import com.acoding.cryptotrackerapp.cryptotracker.presentaion.coin_list.CoinListEvent
import com.acoding.cryptotrackerapp.cryptotracker.presentaion.coin_list.CoinListScreen
import com.acoding.cryptotrackerapp.cryptotracker.presentaion.coin_list.CoinListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AdaptiveCoinListDetailPain(
    viewModel: CoinListViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

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

    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        navigator = navigator,
        modifier = modifier,
        listPane = {
            CoinListScreen(
                state = state,
                onAction = { action ->
                    viewModel.onAction(action)
                    when (action) {
                        is CoinListAction.OnCoinClick -> {
                            navigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Detail
                            )
                        }
                    }
                }
            )
        },
        detailPane = {
            CoinDetailsScreen(
                state = state
            )
        }
    )
}