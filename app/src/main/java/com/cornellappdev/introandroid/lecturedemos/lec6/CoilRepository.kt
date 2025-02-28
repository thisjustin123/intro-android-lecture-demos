package com.cornellappdev.introandroid.lecturedemos.lec6

import android.content.Context
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoilRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    /**
     * Loads and returns an image from a URL using Coil.
     */
    suspend fun loadImageFromURL(url: String): ImageBitmap {
        val imageLoader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(url)
            .build()

        val result = imageLoader.execute(request)
        if (result is SuccessResult) {
            return (result.drawable.toBitmap()).asImageBitmap()
        }

        Log.e("CoilRepository", "Failed to load image from URL: $url")

        return ImageBitmap(1, 1)
    }
}
