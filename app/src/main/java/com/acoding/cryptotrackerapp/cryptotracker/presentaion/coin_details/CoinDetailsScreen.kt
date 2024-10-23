package com.acoding.cryptotrackerapp.cryptotracker.presentaion.coin_details

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.acoding.cryptotrackerapp.R
import com.acoding.cryptotrackerapp.cryptotracker.presentaion.coin_details.components.InfoCard
import com.acoding.cryptotrackerapp.cryptotracker.presentaion.coin_list.CoinListState
import com.acoding.cryptotrackerapp.cryptotracker.presentaion.coin_list.components.previewCoin
import com.acoding.cryptotrackerapp.cryptotracker.presentaion.model.toDisplayableNumber
import com.acoding.cryptotrackerapp.ui.theme.CryptoTrackerTheme
import com.acoding.cryptotrackerapp.ui.theme.greenBackground

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailsScreen(
    state: CoinListState,
    modifier: Modifier = Modifier
) {
    val contentColor = if (!isSystemInDarkTheme()) Color.Black else Color.White

    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        val coin = state.selectedCoin
        if (coin != null) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(coin.iconRes),
                    contentDescription = coin.name,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(100.dp)
                )
                Text(
                    text = coin.name,
                    color = contentColor,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = coin.symbol,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    color = contentColor,
                )
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    InfoCard(
                        title = stringResource(R.string.market_cap),
                        formattedText = "$ ${coin.marketCapUsd.formattedValue}",
                        icon = ImageVector.vectorResource(R.drawable.stock)
                    )
                    InfoCard(
                        title = stringResource(R.string.price),
                        formattedText = "$ ${coin.priceUsd.formattedValue}",
                        icon = ImageVector.vectorResource(R.drawable.dollar)
                    )
                    val absoluteChangeFormatted =
                        (coin.priceUsd.value * (coin.changePercent24Hr.value / 100))
                            .toDisplayableNumber()
                    val isPositive = coin.changePercent24Hr.value > 0.0
                    val cardContentColor = if(isPositive) {
                        if(isSystemInDarkTheme()) Color.Green else greenBackground
                    } else {
                        MaterialTheme.colorScheme.error
                    }
                    InfoCard(
                        title = stringResource(R.string.change_last_24_hours),
                        formattedText = "$ ${absoluteChangeFormatted.formattedValue}",
                        icon = if(isPositive) ImageVector.vectorResource(R.drawable.trending)
                        else ImageVector.vectorResource(R.drawable.trending_down),
                        contentColor = cardContentColor
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun CoinDetailsScreenPreview() {
    CryptoTrackerTheme {
        CoinDetailsScreen(
            state = CoinListState().copy(
                selectedCoin = previewCoin
            )
        )
    }
}