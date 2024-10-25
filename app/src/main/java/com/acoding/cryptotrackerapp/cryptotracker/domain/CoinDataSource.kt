package com.acoding.cryptotrackerapp.cryptotracker.domain

import com.acoding.cryptotrackerapp.core.domain.util.NetworkError
import com.acoding.cryptotrackerapp.core.domain.util.Result
import com.acoding.cryptotrackerapp.cryptotracker.domain.model.Coin
import com.acoding.cryptotrackerapp.cryptotracker.domain.model.CoinPrice
import java.time.ZonedDateTime

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>

    suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError>
}