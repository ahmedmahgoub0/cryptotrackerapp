package com.acoding.cryptotrackerapp.cryptotracker.presentaion.coin_list

import com.acoding.cryptotrackerapp.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError) : CoinListEvent
}