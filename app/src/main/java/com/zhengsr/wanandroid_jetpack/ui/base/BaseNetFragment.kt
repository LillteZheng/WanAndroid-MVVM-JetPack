package com.zhengsr.wanandroid_jetpack.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.zhengsr.wanandroid_jetpack.MainApplication
import com.zhengsr.wanandroid_jetpack.R

/**
 * @author by zhengshaorui 2022/2/8
 * describe：
 */
abstract class BaseNetFragment<T : ViewModel> : BaseFragment<T>() {
    private val NORMAL_VIEW = 1
    private val LOADING_VIEW = 2
    private val ERROR_VIEW = 3
    private var normalView: View? = null
    private var errorView: View? = null
    private var loadingView: View? = null


    override fun initData(view: View?) {
        super.initData(view)
        normalView = view?.findViewById(R.id.normal_view)
        if (normalView == null) {
            throw RuntimeException("you show add view use normal_view id")
        }
        if (normalView!!.parent !is ViewGroup) {
            throw RuntimeException("normal_view must be viewgroup")
        }

        val parent = normalView!!.getParent() as ViewGroup
        loadingView =
            LayoutInflater.from(mActivity).inflate(R.layout.loadingview_layout, parent, false)
        errorView =
            LayoutInflater.from(mActivity).inflate(R.layout.load_error_layout, parent, false)

        /**
         * 把三个 view 都添加进来,并先让它不显示
         */
        parent.addView(errorView)
        parent.addView(loadingView)
        errorView?.visibility = View.GONE
        loadingView?.visibility = View.GONE
        normalView?.visibility = View.GONE
    }

    open fun showLoading() {
        showCurrentView(LOADING_VIEW)
    }

    open fun showError() {
        showCurrentView(ERROR_VIEW)
    }

    open fun showSuccess(delayTime: Long = 0L) {
        MainApplication.handler.postDelayed({
            showCurrentView(NORMAL_VIEW)
        }, delayTime)
    }


    /**
     * 切换不同的页面
     * @param type
     */
    private fun showCurrentView(type: Int) {
        normalView?.visibility = View.GONE
        loadingView?.visibility = View.GONE
        errorView?.visibility = View.GONE
        when (type) {
            NORMAL_VIEW -> {
                normalView?.visibility = View.VISIBLE
            }
            LOADING_VIEW -> {
                loadingView?.visibility = View.VISIBLE
            }
            ERROR_VIEW -> {
                errorView?.visibility = View.VISIBLE
            }
        }
    }
}