package com.cornellappdev.introandroid.lecturedemos.lec6.retrofit

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    private val retrofitInstance: RetrofitInstance
): ViewModel() {

}
