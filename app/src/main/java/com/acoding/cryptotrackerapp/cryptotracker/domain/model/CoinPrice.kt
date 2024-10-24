package com.acoding.cryptotrackerapp.cryptotracker.domain.model

import java.time.ZonedDateTime

data class CoinPrice(
    val priceUsd: Double,
    val time: ZonedDateTime
)
