package com.zhengsr.wanandroid_jetpack.ui.window

import android.content.Context
import android.view.ViewGroup
import com.zhengsr.wanandroid_jetpack.R

/**
 * @author by zhengshaorui 2022/2/8
 * describe：
 */
class CommonDialog(val context: Context) {
    private val TAG = "CommonDialog"
    private val cusWindow: CusDialog = CusDialog.Builder()
        .setContext(context)
        .setLayoutId(R.layout.loadingview_layout)
        .setWidth(ViewGroup.LayoutParams.MATCH_PARENT)
        .setHeight(ViewGroup.LayoutParams.MATCH_PARENT)
        .disableBackKey(true)
        .showBackground(true)
        .setOutSideDimiss(false)
        .builder()

    init {
        dismiss()
    }

    fun dismiss() {
        cusWindow.dismiss()
    }

    fun show() {
        cusWindow.show()
    }
}