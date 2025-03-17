package com.cornellappdev.introandroid.lecturedemos.lec2

import androidx.compose.ui.graphics.Color

enum class Importance(val color: Color, val text: String) {
    LOW(Color.Green, "Low"),
    MEDIUM(Color.Yellow, "Medium"),
    HIGH(Color.Red, "High")
}
