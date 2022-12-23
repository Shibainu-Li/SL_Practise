package com.shibainu.li.sl_practiseproject.http

import com.shibainu.li.httplib.base.BaseRetrofitClient
import okhttp3.OkHttpClient

object HttpManager : BaseRetrofitClient() {
    val service by lazy { getService(ApiService::class.java,ApiService.BASE_URL) }

    override fun handleBuilder(builder: OkHttpClient.Builder) = Unit


}