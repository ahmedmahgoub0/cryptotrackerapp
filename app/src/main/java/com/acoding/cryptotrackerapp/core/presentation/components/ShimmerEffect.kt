package com.acoding.cryptotrackerapp.core.presentation.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

fun Modifier.shimmerEffect() = composed {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition(
        label = "shimmer_transition"
    )
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * (size.width.toFloat() + size.height.toFloat()),
        targetValue = 2 * (size.width.toFloat() + size.height.toFloat()),
        animationSpec = infiniteRepeatable(tween(1500)),
        label = "startOffsetX"
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                MaterialTheme.colorScheme.surfaceVariant,
                Color.Gray,
                MaterialTheme.colorScheme.surfaceVariant
            ),
            start = Offset(x = startOffsetX, y = 0f),
            end = Offset(
                x = startOffsetX + size.width.toFloat(),
                y = size.height.toFloat()
            )
        )
    ).onGloballyPositioned {
        size = it.size
    }
}