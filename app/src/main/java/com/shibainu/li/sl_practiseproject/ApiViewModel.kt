package com.shibainu.li.sl_practiseproject

import com.shibainu.li.baselib.BaseViewModel
import com.shibainu.li.httplib.entity.ApiResponse
import com.shibainu.li.sl_practiseproject.http.HttpRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ApiViewModel : BaseViewModel() {
    private val re by lazy { HttpRepository() }

    private val _uiState = MutableStateFlow<ApiResponse<String>>(ApiResponse())
    val uiState: StateFlow<ApiResponse<String>> = _uiState.asStateFlow()


    suspend fun requestTest(){
        _uiState.value = re.getTest()
    }
}