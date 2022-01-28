package com.zhengsr.wanandroid_jetpack.ui.base

import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar
import com.kunminx.architecture.ui.page.DataBindingActivity
import com.zhengsr.wanandroid_jetpack.MainApplication
import com.zhengsr.wanandroid_jetpack.ui.data.ShareViewModel
import dev.utils.app.BarUtils

/**
 * @author by zhengshaorui 2022/1/22
 * describe：Activity 基类
 */
abstract class BaseActivity<T : ViewModel> : DataBindingActivity() {

    protected lateinit var state: T
    protected val event: ShareViewModel by lazy {
        getApplicationViewModel(ShareViewModel::class.java)
    }

    private val fragmentProvider by lazy {
        ViewModelProvider(this@BaseActivity)
    }
    private val applicationProvider by lazy {
        val mainApplication = this@BaseActivity.applicationContext as MainApplication
        ViewModelProvider(mainApplication)
    }


    protected fun <T :ViewModel> getViewModel(moudleClass:Class<T>):T{
        return fragmentProvider[moudleClass]
    }
    protected fun <T :ViewModel> getApplicationViewModel(moudleClass:Class<T>):T{
        return applicationProvider[moudleClass]
    }

    override fun onDestroy() {
        super.onDestroy()

    }

}