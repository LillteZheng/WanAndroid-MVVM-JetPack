package com.zhengsr.wanandroid_jetpack.utils

import android.util.Log
import androidx.lifecycle.ViewModel
import com.zhengsr.wanandroid_jetpack.bean.BannerBean
import com.zhengsr.wanandroid_jetpack.bean.BaseResponse

/**
 * @author by zhengshaorui 2022/2/8
 * describe：
 */
/**
 * 对返回数据进行判断，并处理相应逻辑
 * > 成功: [onSuccess] or 失败: [onFailed] or 登录失效: [onFailed4Login]
 */
private const val TAG = "NetResult"
inline fun <T> BaseResponse<T>.judge(
    crossinline onSuccess: BaseResponse<T>.() -> Unit = {},
    crossinline onFailed: BaseResponse<T>.() -> Unit = {},
    crossinline onFailed4Login: BaseResponse<MutableList<BannerBean>>.() -> Unit = { false }
) {
    when (errorCode) {
        NET_RESPONSE_CODE_SUCCESS -> {
            // 请求成功
            onSuccess.invoke(this)
        }
        NET_RESPONSE_CODE_LOGIN_FAILED -> {
            // 登录失效，需要重新登录
           /* if (onFailed4Login.invoke(this)) {
                // 已消费事件
                return
            }*/
            /*(AppManager.peekActivity() as? BaseActivity<*, *>)?.run {
                viewModel.snackbarData.value = SnackbarModel(
                    content = errorMsg,
                    duration = Snackbar.LENGTH_INDEFINITE,
                    actionText = R.string.app_go_login.string,
                    onAction = {
                        // 登录失败，需要重新登录
                        LoginActivity.actionStart(this)
                    }
                )
            }*/
            Log.d("TAG", "需要重新登录")
        }
        else -> {
            // 请求失败
            onFailed.invoke(this)
        }
    }
}

/** 默认提示异常的方法块 */
val ViewModel.defaultFailedBlock: BaseResponse<*>.() -> Unit
    get() = {
       // snackbarData.value = toSnackbarModel()
        //todo 待处理统一错误提示
    }