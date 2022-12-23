package com.shibainu.li.sl_practiseproject

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.shibainu.li.baselib.BaseActivity
import com.shibainu.li.cpplibs.CppTestMainActivity
import com.shibainu.li.httplib.entity.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val vm by lazy {  ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(ApiViewModel::class.java) }

    override fun initViews() {
        findViewById<View>(R.id.cpp_test).setOnClickListener {
            startActivity(Intent(this,CppTestMainActivity::class.java))
        }

        findViewById<View>(R.id.http_test).setOnClickListener {
            lifecycleScope.launch {
                vm.requestTest()
            }
        }
    }

    override fun initConfig() {
        Log.d("sl_practiseproject","initConfig")
        showLoading()
        dismissLoading()
        vm.uiState.collectIn(this, Lifecycle.State.STARTED) {
            onSuccess = { result: String? ->
                Log.d("sl_practiseproject","result:$result")
            }

            onComplete = { Log.i("sl_practiseproject", ": onComplete") }

            onFailed = { code, msg -> Log.d("sl_practiseproject","errorCode: $code   errorMsg: $msg") }

            onError = { Log.d("sl_practiseproject","${it.toString()}") }
        }


    }

}

fun <T> Flow<ApiResponse<T>>.collectIn(
    lifecycleOwner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    listenerBuilder: ResultBuilder<T>.() -> Unit,
): Job = lifecycleOwner.lifecycleScope.launch {
    flowWithLifecycle(lifecycleOwner.lifecycle,minActiveState).collect { apiResponse: ApiResponse<T> ->
        apiResponse.parseData(listenerBuilder)
    }
}



class ResultBuilder<T> {
    var onSuccess: (data: T?) -> Unit = {}
    var onDataEmpty: () -> Unit = {}
    var onFailed: (errorCode: Int?, errorMsg: String?) -> Unit = { _, errorMsg ->
        errorMsg?.let {  }
    }
    var onError: (e: Throwable) -> Unit = { e ->
        e.message?.let {  }
    }
    var onComplete: () -> Unit = {}
}

fun <T> ApiResponse<T>.parseData(listenerBuilder: ResultBuilder<T>.() -> Unit) {
    val listener = ResultBuilder<T>().also(listenerBuilder)
    when (this) {
        is ApiSuccessResponse -> listener.onSuccess(this.response)
        is ApiEmptyResponse -> listener.onDataEmpty()
        is ApiFailedResponse -> listener.onFailed(this.errorCode, this.errorMsg)
        is ApiErrorResponse -> listener.onError(this.throwable)
    }
    listener.onComplete()
}