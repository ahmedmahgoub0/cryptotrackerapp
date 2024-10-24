package com.acoding.cryptotrackerapp.cryptotracker.presentaion.model

import androidx.annotation.DrawableRes
import com.acoding.cryptotrackerapp.core.presentation.util.getDrawableIdForCoin
import com.acoding.cryptotrackerapp.cryptotracker.domain.model.Coin
import java.text.NumberFormat
import java.util.Locale

data class CoinUi(
    val id: String,
    val rank: String,
    val symbol: String,
    val name: String,
    val marketCapUsd: DisplayableNumber,
    val priceUsd: DisplayableNumber,
    val changePercent24Hr: DisplayableNumber,
    @DrawableRes val iconRes: Int
)

fun Coin.toCoinUi(): CoinUi {
    return CoinUi(
        id = id,
        rank = rank,
        symbol = symbol,
        name = name,
        marketCapUsd = marketCapUsd.toDisplayableNumber(),
        priceUsd = priceUsd.toDisplayableNumber(),
        changePercent24Hr = changePercent24Hr.toDisplayableNumber(),
        iconRes = getDrawableIdForCoin(symbol)
    )
}

data class DisplayableNumber(
    val value: Double,
    val formattedValue: String
)

fun Double.toDisplayableNumber(): DisplayableNumber {
    val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
    }
    return DisplayableNumber(
        value = this,
        formattedValue = numberFormat.format(this)
    )
}