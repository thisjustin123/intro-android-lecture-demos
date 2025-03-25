package com.cornellappdev.introandroid.lecturedemos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.cornellappdev.introandroid.lecturedemos.lec3.ui.screen.AnimationScreen
import com.cornellappdev.introandroid.lecturedemos.ui.theme.LectureDemosTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LectureDemosTheme {
                // Lecture 3
                AnimationScreen()

                // Lecture 4
//                BankHistoryScreen()

                // Lecture 5
//                TickerScreen()

                // Lecture 6
//                CatScreen()
            }
        }
    }
}
