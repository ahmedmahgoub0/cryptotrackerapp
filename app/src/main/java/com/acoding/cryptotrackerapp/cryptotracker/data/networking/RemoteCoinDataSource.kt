package com.acoding.cryptotrackerapp.cryptotracker.data.networking

import com.acoding.cryptotrackerapp.core.data.networking.constructUrl
import com.acoding.cryptotrackerapp.core.data.networking.safeCall
import com.acoding.cryptotrackerapp.core.domain.util.NetworkError
import com.acoding.cryptotrackerapp.core.domain.util.Result
import com.acoding.cryptotrackerapp.core.domain.util.map
import com.acoding.cryptotrackerapp.cryptotracker.data.mappers.toCoin
import com.acoding.cryptotrackerapp.cryptotracker.data.networking.dto.CoinsResponseDto
import com.acoding.cryptotrackerapp.cryptotracker.domain.Coin
import com.acoding.cryptotrackerapp.cryptotracker.domain.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource(
    private val client: HttpClient
) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            client.get(constructUrl("/assets"))
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }
}