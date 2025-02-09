package com.cornellappdev.introandroid.lecturedemos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.cornellappdev.introandroid.lecturedemos.lec4.BankHistoryScreen
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
//                MainScreen()

                // Lecture 4
                BankHistoryScreen()
            }
        }
    }
}
