package com.shibainu.li.sl_practiseproject.http

import com.shibainu.li.httplib.entity.ApiResponse
import retrofit2.http.GET

interface ApiService {
    companion object {
        const val BASE_URL = "https://www.baidu.com/"
    }

    @GET("s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=神医喜来乐&fenlei=256&oq=%25E7%25A5%259E%25E5%258C%25BB%25E5%2596%259C%25E6%259D%25A5%25E4%25B9%2590%25E8%2580%2581%25E5%25A9%2586%25E4%25B8%25BA%25E4%25BB%2580%25E4%25B9%2588%25E8%2587%25AA%25E5%25B0%25BD&rsv_pq=ee857b8500021c51&rsv_t=904dgJJG6UZ5FIIBZu4ToznYvu66vhghqLYc2IH7bp5xFh3zAFEhFMNfriM&rqlang=cn&rsv_enter=1&rsv_dl=tb&rsv_btype=t&rsv_sug3=63&rsv_sug1=14&rsv_sug7=100&bs=神医喜来乐老婆为什么自尽")
    suspend fun getTest():ApiResponse<String>


}