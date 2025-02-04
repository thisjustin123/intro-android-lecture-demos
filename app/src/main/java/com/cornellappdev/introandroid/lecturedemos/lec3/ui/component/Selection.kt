package com.cornellappdev.introandroid.lecturedemos.lec3.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Selection(selected: Boolean, onSelect: () -> Unit) {
    val regularSize = if (selected) 32f else 0f

    // TODO: Swap use of regularSize to animatedSize and see what happens!
    val animatedSize by animateFloatAsState(targetValue = regularSize, label = "Animate Selection")

    Column(modifier = Modifier.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onSelect()
    }) {
        Text(text = "Select")

        Box(contentAlignment = Alignment.Center) {
            Surface(
                shape = CircleShape,
                border = BorderStroke(2.dp, Color.Black),
                color = Color.Transparent,
                modifier = Modifier.size(40.dp)
            ) {}
            Surface(
                shape = CircleShape, color = Color.Black, modifier = Modifier.size(animatedSize.dp)
            ) {}
        }
    }
}
