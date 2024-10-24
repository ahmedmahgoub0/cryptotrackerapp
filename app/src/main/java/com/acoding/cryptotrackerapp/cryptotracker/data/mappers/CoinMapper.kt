package com.acoding.cryptotrackerapp.cryptotracker.data.mappers

import com.acoding.cryptotrackerapp.cryptotracker.data.networking.dto.CoinDto
import com.acoding.cryptotrackerapp.cryptotracker.data.networking.dto.CoinPriceDto
import com.acoding.cryptotrackerapp.cryptotracker.domain.model.Coin
import com.acoding.cryptotrackerapp.cryptotracker.domain.model.CoinPrice
import java.time.Instant
import java.time.ZoneId

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        rank = rank,
        symbol = symbol,
        name = name,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}

fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
        priceUsd = priceUsd,
        time = Instant
            .ofEpochMilli(time)
            .atZone(ZoneId.of("UTC"))
    )
}