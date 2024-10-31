package com.acoding.cryptotrackerapp.cryptotracker.presentaion.coin_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.acoding.cryptotrackerapp.core.presentation.components.shimmerEffect
import com.acoding.cryptotrackerapp.ui.theme.CryptoTrackerTheme

@Composable
fun CoinListShimmerLayout(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(10) {
            CoinListItemShimmerLayout(modifier = modifier.fillMaxWidth())
        }
    }
}

@Composable
private fun CoinListItemShimmerLayout(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(85.dp)
                .clip(shape = CircleShape)
                .shimmerEffect()
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(12.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .shimmerEffect()
            )
            Spacer(modifier =Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(12.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .shimmerEffect()
            )
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(12.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .shimmerEffect()
            )
            Spacer(modifier =Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .width(70.dp)
                    .height(12.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .shimmerEffect()
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun CoinListItemShimmerPrev() {
    CryptoTrackerTheme {
        CoinListShimmerLayout()
    }
}