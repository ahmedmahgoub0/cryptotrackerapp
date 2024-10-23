package com.acoding.cryptotrackerapp.cryptotracker.presentaion.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acoding.cryptotrackerapp.core.domain.util.onError
import com.acoding.cryptotrackerapp.core.domain.util.onSuccess
import com.acoding.cryptotrackerapp.cryptotracker.domain.CoinDataSource
import com.acoding.cryptotrackerapp.cryptotracker.presentaion.model.toCoinUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val coinDataSource: CoinDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state = _state
        .onStart { loadCoins() }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = CoinListState()
        )

    private val _event = Channel<CoinListEvent>()
    val event = _event.receiveAsFlow()

    fun onAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.OnCoinClick -> {
                _state.update {
                    it.copy(
                        selectedCoin = action.coinUi
                    )
                }
            }
        }
    }

    private fun loadCoins() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            coinDataSource.getCoins()
                .onSuccess { coins ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        coins = coins.map { it.toCoinUi() }
                    )
                }
                .onError { error ->
                    _state.value = _state.value.copy(isLoading = false)
                    _event.send(CoinListEvent.Error(error))
                }
        }
    }
}