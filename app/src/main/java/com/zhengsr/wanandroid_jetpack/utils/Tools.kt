package com.zhengsr.wanandroid_jetpack.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author by zhengshaorui 2022/1/28
 * describe：通用顶层函数
 */
// ViewModel 协程扩展函数
fun ViewModel.scopeIo(block: suspend CoroutineScope.() -> Unit){
    this.viewModelScope.launch(Dispatchers.IO){
        block.invoke(this)
    }
}
fun ViewModel.scopeMain(block: suspend CoroutineScope.() -> Unit){
    this.viewModelScope.launch(Dispatchers.Main){
        block.invoke(this)
    }
}
fun ViewModel.scopeDe(block: suspend CoroutineScope.() -> Unit){
    this.viewModelScope.launch(Dispatchers.Default){
        block.invoke(this)
    }
}
suspend fun <T> withIo(block: suspend CoroutineScope.() -> T) =
    withContext(Dispatchers.IO) { block(this) }
suspend fun <T> withDe(block: suspend CoroutineScope.() -> T) =
    withContext(Dispatchers.Default) { block(this) }

suspend fun <T> withMain(block: suspend CoroutineScope.() -> T) =
    withContext(Dispatchers.Main) { block(this) }

