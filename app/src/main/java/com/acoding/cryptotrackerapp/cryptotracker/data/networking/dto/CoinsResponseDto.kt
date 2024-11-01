package com.acoding.cryptotrackerapp.cryptotracker.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinsResponseDto(
    val data: List<CoinDto>,
    val timestamp: Long
)
