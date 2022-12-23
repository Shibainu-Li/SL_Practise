package com.shibainu.li.sl_practiseproject.http

import com.shibainu.li.httplib.base.BaseRepository
import com.shibainu.li.httplib.entity.ApiResponse

class HttpRepository : BaseRepository() {

    private val mService by lazy { HttpManager.service }

    suspend fun getTest(): ApiResponse<String>{
        return executeHttp { mService.getTest() }
    }

}