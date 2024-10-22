package com.acoding.cryptotrackerapp.cryptotracker.data.mappers

import com.acoding.cryptotrackerapp.cryptotracker.data.networking.dto.CoinDto
import com.acoding.cryptotrackerapp.cryptotracker.domain.Coin

fun CoinDto.toCoin() = Coin(
    id = id,
    rank = rank,
    symbol = symbol,
    name = name,
    marketCapUsd = marketCapUsd,
    priceUsd = priceUsd,
    changePercent24Hr = changePercent24Hr
)