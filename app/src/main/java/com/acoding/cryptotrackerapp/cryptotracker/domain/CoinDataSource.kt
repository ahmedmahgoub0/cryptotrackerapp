package com.acoding.cryptotrackerapp.cryptotracker.domain

import com.acoding.cryptotrackerapp.core.domain.util.NetworkError
import com.acoding.cryptotrackerapp.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}