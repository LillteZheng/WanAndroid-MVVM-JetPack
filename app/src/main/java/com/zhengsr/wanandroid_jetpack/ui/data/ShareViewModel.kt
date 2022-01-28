package com.zhengsr.wanandroid_jetpack.ui.data

import androidx.lifecycle.ViewModel
import com.kunminx.architecture.ui.callback.UnPeekLiveData

/**
 * @author by zhengshaorui 2022/1/28
 * describe：
 */
class ShareViewModel :ViewModel() {
    val drawerOpenOrClose = UnPeekLiveData<Boolean>()
}