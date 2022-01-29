package com.zhengsr.wanandroid_jetpack.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.kunminx.architecture.ui.page.DataBindingFragment
import com.zhengsr.wanandroid_jetpack.MainApplication
import com.zhengsr.wanandroid_jetpack.ui.data.ShareViewModel
import java.lang.reflect.Modifier
import kotlin.contracts.ReturnsNotNull

/**
 * @author by zhengshaorui 2022/1/24
 * describe：基类fragment
 */
abstract class BaseFragment<T : ViewModel> : DataBindingFragment() {
    private val TAG = "BaseFragment"
    protected lateinit var state: T
    protected val event: ShareViewModel by lazy {
        getApplicationViewModel(ShareViewModel::class.java)
    }

    private val fragmentProvider by lazy {
        ViewModelProvider(mActivity)
    }
    private val applicationProvider by lazy {
        val mainApplication = mActivity.applicationContext as MainApplication
        ViewModelProvider(mainApplication)
    }


    protected fun <T : ViewModel> getViewModel(moudleClass: Class<T>): T {
        return fragmentProvider[moudleClass]
    }

    private fun <T : ViewModel> getApplicationViewModel(moudleClass: Class<T>): T {
        return applicationProvider[moudleClass]
    }

    protected open fun nav(): NavController? {
        return NavHostFragment.findNavController(this)
    }
}