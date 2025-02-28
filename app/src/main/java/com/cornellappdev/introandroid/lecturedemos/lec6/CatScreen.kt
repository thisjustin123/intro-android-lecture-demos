package com.cornellappdev.introandroid.lecturedemos.lec6

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cornellappdev.introandroid.lecturedemos.lec6.components.ImageCard

@Composable
fun CatScreen(
    catViewModel: CatViewModel = hiltViewModel()
) {
    val uiState = catViewModel.uiStateFlow.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(.2f))

        Text(
            text = uiState.titleText,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.weight(.2f))

        ImageCard(
            imageBitmap = uiState.imageData,
            modifier = Modifier
                .fillMaxWidth(.8f)
                .fillMaxHeight(.5f)
        )

        Spacer(Modifier.weight(.2f))

        TextField(
            value = uiState.query,
            onValueChange = { catViewModel.onQueryChange(it) },
            modifier = Modifier.fillMaxWidth(.6f)
        )

        Spacer(Modifier.weight(.1f))

        Button(
            onClick = {
                catViewModel.onSubmit()
            }
        ) {
            Text(
                text = "Submit",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.weight(.2f))
    }
}
