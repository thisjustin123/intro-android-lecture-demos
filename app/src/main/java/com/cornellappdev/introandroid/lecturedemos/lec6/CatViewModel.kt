package com.cornellappdev.introandroid.lecturedemos.lec6

import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cornellappdev.introandroid.lecturedemos.lec6.retrofit.Cat
import com.cornellappdev.introandroid.lecturedemos.lec6.retrofit.RetrofitInstance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    private val retrofitInstance: RetrofitInstance,
    private val coilRepository: CoilRepository
) : ViewModel() {

    private val _uiStateFlow: MutableStateFlow<UIState> = MutableStateFlow(
        UIState(
            cat = null,
            loading = false,
            query = "",
            imageData = null
        )
    )
    val uiStateFlow = _uiStateFlow.asStateFlow()

    data class UIState(
        val cat: Cat?,
        val loading: Boolean,
        val query: String,
        val imageData: ImageBitmap? = null
    ) {
        val titleText: String
            get() {
                if (loading) {
                    return "Loading..."
                } else if (cat != null) {
                    return cat.name
                }
                return "Search for a Cat!"
            }

        val buttonEnabled
            get() = !loading
    }

    fun onQueryChange(query: String) {
        _uiStateFlow.value = _uiStateFlow.value.copy(query = query)
    }

    fun onSubmit() {
        val query = _uiStateFlow.value.query
        _uiStateFlow.value = _uiStateFlow.value.copy(
            loading = true,
            query = "",
            imageData = null
        )

        // TODO (1):
        //  1. Use `FakeCatNetworkingRepository` to fetch cats... but you need a coroutine!
        //  2. Use `coilRepository` to load the image...
        //  3. Error handle--super important with networking!
        //  4. Update the UI if a success!

        // TODO (2):
        //  Use the actual retrofit code, instead! (And finish the retrofit code in CatsApiService.kt)

        viewModelScope.launch {
            try {
                // Even though our networking functions hang for a bit, the UI doesn't freeze.
                //  And we can continue coding in this block like normal...
                val cats = retrofitInstance.catsApiService.getCats(
                    name = query
                )

                if (cats.isEmpty()) {
                    _uiStateFlow.value = _uiStateFlow.value.copy(
                        cat = null,
                        loading = false
                    )
                    return@launch
                }

                _uiStateFlow.value = _uiStateFlow.value.copy(
                    cat = cats[0],
                    loading = false
                )

                val bitmap = coilRepository.loadImageFromURL(cats[0].imageLink)

                _uiStateFlow.value = _uiStateFlow.value.copy(
                    imageData = bitmap
                )
            } catch (e: Exception) {
                Log.e("CatViewModel", "Failed to fetch cats:", e)
                _uiStateFlow.value = _uiStateFlow.value.copy(
                    cat = null,
                    loading = false
                )
            }
        }
    }
}
