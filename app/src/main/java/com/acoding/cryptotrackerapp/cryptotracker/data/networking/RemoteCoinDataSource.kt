package com.acoding.cryptotrackerapp.cryptotracker.data.networking

import com.acoding.cryptotrackerapp.core.data.networking.constructUrl
import com.acoding.cryptotrackerapp.core.data.networking.safeCall
import com.acoding.cryptotrackerapp.core.domain.util.NetworkError
import com.acoding.cryptotrackerapp.core.domain.util.Result
import com.acoding.cryptotrackerapp.core.domain.util.map
import com.acoding.cryptotrackerapp.cryptotracker.data.mappers.toCoin
import com.acoding.cryptotrackerapp.cryptotracker.data.mappers.toCoinPrice
import com.acoding.cryptotrackerapp.cryptotracker.data.networking.dto.CoinHistoryResponseDto
import com.acoding.cryptotrackerapp.cryptotracker.data.networking.dto.CoinsResponseDto
import com.acoding.cryptotrackerapp.cryptotracker.domain.CoinDataSource
import com.acoding.cryptotrackerapp.cryptotracker.domain.model.Coin
import com.acoding.cryptotrackerapp.cryptotracker.domain.model.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

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

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startMillis = start.withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMillis = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        return safeCall<CoinHistoryResponseDto> {
            client.get(
                urlString = constructUrl("/assets/$coinId/history")
            ) {
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }
}