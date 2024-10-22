package com.acoding.cryptotrackerapp.cryptotracker.domain

data class Coin(
    val id: String,
    val rank: String,
    val symbol: String,
    val name: String,
    val marketCapUsd: Double,
    val priceUsd: Double,
    val changePercent24Hr: Double
)