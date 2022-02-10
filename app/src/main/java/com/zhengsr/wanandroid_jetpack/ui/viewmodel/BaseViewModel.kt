package com.zhengsr.wanandroid_jetpack.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhengsr.wanandroid_jetpack.bean.BaseResponse

/**
 * @author by zhengshaorui 2022/2/9
 * describe：
 */
open class BaseViewModel :ViewModel() {
    //错误状态
    val failStatus = MutableLiveData<Boolean>()

    /** 默认提示异常的方法块 */
    fun defaultFailedBlock() : BaseResponse<*>.() -> Unit = {
        Log.d("TAG", "zsr defaultFailedBlock: ")
        failStatus.postValue(true)
    }
    /** 默认提示异常的方法块 */
    fun defaultFail()  {
        failStatus.postValue(true)
    }
}