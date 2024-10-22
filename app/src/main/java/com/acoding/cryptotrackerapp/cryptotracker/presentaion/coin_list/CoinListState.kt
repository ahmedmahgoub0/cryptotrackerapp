package com.acoding.cryptotrackerapp.cryptotracker.presentaion.coin_list

import androidx.compose.runtime.Immutable
import com.acoding.cryptotrackerapp.cryptotracker.presentaion.model.CoinUi

@Immutable
data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinUi> = emptyList(),
    val selectedCoin: CoinUi? = null
)