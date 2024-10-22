package com.acoding.cryptotrackerapp.cryptotracker.presentaion.coin_list

import com.acoding.cryptotrackerapp.cryptotracker.presentaion.model.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi) : CoinListAction
}