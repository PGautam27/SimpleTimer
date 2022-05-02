package com.example.composetimer.presentation.component


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composetimer.ui.theme.*

@Composable
fun Controllers(
    modifier: Modifier = Modifier,
    onPause: () -> Unit,
    onStart: () -> Unit,
    onStop: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Pause(onPause = onPause)
        Start(onStart = onStart)
        Stop(onStop = onStop)
    }
}

@Composable
internal fun Stop(onStop: () -> Unit) {
    Button(
        color = purpleLightest,
        onClick = onStop
    ) {
        Icon(
            imageVector = Icons.Rounded.Close,
            contentDescription = "Stop",
            tint = purpleDark,
            modifier = Modifier
                .size(45.dp)
                .align(alignment = Alignment.Center)
        )
    }
}

@Composable
internal fun Start(onStart: () -> Unit) {
    Button(
        color = green,
        onClick = onStart
    ) {
        Text(
            text = "START",
            style = MaterialTheme.typography.button,
            color = greenDark,
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}

@Composable
internal fun Pause(onPause: () -> Unit) {
    Button(
        color = purpleLightest,
        onClick = onPause
    ) {
        Icon(
            imageVector = Icons.Rounded.Lock,
            contentDescription = "Pause",
            tint = purpleDark,
            modifier = Modifier
                .size(35.dp)
                .align(alignment = Alignment.Center)
        )
    }
}

@Composable
internal fun Button(
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val interaction = remember { MutableInteractionSource() }
    val isPressed by interaction.collectIsPressedAsState()
    val colorAnimated by animateColorAsState(
        targetValue = if (isPressed) color else Color.White,
        animationSpec = tween(400)
    )
    val elevation by animateDpAsState(
        targetValue = if (isPressed) 3.dp else 8.dp,
        animationSpec = tween(400)
    )
    val brush = Brush.verticalGradient(listOf(color, colorAnimated))
    Box(
        modifier
            .shadow(elevation = elevation, shape = CircleShape)
            .border(width = 5.dp, color = Color.White, shape = CircleShape)
            .background(brush = brush, shape = CircleShape)
            .clip(CircleShape)
            .width(100.dp)
            .aspectRatio(1f)
            .clickable(
                interactionSource = interaction,
                indication = null,
                onClick = onClick
            ),
        content = content
    )
}
