package com.zhengsr.wanandroid_jetpack

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

/**
 * @author by zhengshaorui 2022/1/24
 * describe：
 */
class MainApplication :Application(),ViewModelStoreOwner{
    private lateinit var viewModelStore : ViewModelStore
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context :Context
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        viewModelStore = ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore = viewModelStore
}