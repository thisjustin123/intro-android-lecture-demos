package com.cornellappdev.introandroid.lecturedemos.lec6

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
        val buttonEnabled
            get() = cat != null && !loading
    }

    fun onSubmit() {
        _uiStateFlow.value = _uiStateFlow.value.copy(
            loading = true,
            query = ""
        )

        viewModelScope.launch {
            // Even though this function hangs for a second, the UI doesn't freeze.
            //  And we can continue coding in this block like normal...
            val cats = retrofitInstance.catsApiService.getCats(_uiStateFlow.value.query)

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

        }
    }
}
