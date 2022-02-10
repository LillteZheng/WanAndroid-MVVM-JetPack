package com.zhengsr.wanandroid_jetpack

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

/**
 * @author by zhengshaorui 2022/1/24
 * describe：
 */
class MainApplication :Application(),ViewModelStoreOwner{
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context :Context
        val handler by lazy { Handler(Looper.getMainLooper()) }
    }
    private lateinit var viewModelStore : ViewModelStore
    override fun onCreate() {
        super.onCreate()
        CrashHandler()
        context = applicationContext
        viewModelStore = ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore = viewModelStore
}