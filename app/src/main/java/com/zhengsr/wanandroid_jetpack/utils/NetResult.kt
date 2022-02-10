package com.zhengsr.wanandroid_jetpack.utils

import android.util.Log
import androidx.lifecycle.ViewModel
import com.zhengsr.wanandroid_jetpack.bean.BannerBean
import com.zhengsr.wanandroid_jetpack.bean.BaseResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @author by zhengshaorui 2022/2/8
 * describe：
 */
/**
 * 对返回数据进行判断，并处理相应逻辑
 * > 成功: [onSuccess] or 失败: [onFailed] or 登录失效: [onFailed4Login]
 */
const val TAG = "NetResult"
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
        }else -> {
            Log.d(TAG, "加载失败: $errorCode $errorMsg")
            // 请求失败
            onFailed.invoke(this)
        }
    }
}

suspend inline fun <T> apiCall(crossinline call: suspend CoroutineScope.() -> BaseResponse<T>): BaseResponse<T> {
    return withContext(Dispatchers.IO) {
        val res: BaseResponse<T>
        try {
            res = call()
        } catch (e: Throwable) {
            Log.e(TAG, "zsr request error $e")
            // 请求出错，将状态码和消息封装为 ResponseResult
            return@withContext ApiException.build(e).toResponse<T>()
        }
        if (res.errorCode == ApiException.CODE_AUTH_INVALID) {
            Log.e("ApiCaller", "request auth invalid")
            // 登录过期，取消协程，跳转登录界面
            // 省略部分代码
           // cancel()
            Log.d(TAG, "需要重新登录")
        }
        return@withContext res
    }
}

// 网络、数据解析错误处理
class ApiException(val code: Int, override val message: String?, override val cause: Throwable? = null)
    : RuntimeException(message, cause) {
    companion object {
        // 网络状态码
        const val CODE_NET_ERROR = 4000
        const val CODE_TIMEOUT = 4080
        const val CODE_JSON_PARSE_ERROR = 4010
        const val CODE_SERVER_ERROR = 5000
        // 业务状态码
        const val CODE_AUTH_INVALID = -1001

        fun build(e: Throwable): ApiException {
            return if (e is HttpException) {
                ApiException(CODE_NET_ERROR, "网络异常(${e.code()},${e.message()})")
            } else if (e is UnknownHostException) {
                ApiException(CODE_NET_ERROR, "网络连接失败，请检查后再试")
            } else if (e is ConnectTimeoutException || e is SocketTimeoutException) {
                ApiException(CODE_TIMEOUT, "请求超时，请稍后再试")
            } else if (e is IOException) {
                ApiException(CODE_NET_ERROR, "网络异常(${e.message})")
            } else if (e is com.alibaba.fastjson.JSONException || e is JSONException) {
                // Json解析失败
                ApiException(CODE_JSON_PARSE_ERROR, "数据解析错误，请稍后再试")
            } else {
                ApiException(CODE_SERVER_ERROR, "系统错误(${e.message})")
            }
        }
    }
    fun <T> toResponse(): BaseResponse<T> {
        val baseResponse = BaseResponse<T>()
        baseResponse.errorCode = code
        baseResponse.errorMsg = message
        return baseResponse
    }
}


