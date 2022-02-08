package com.zhengsr.wanandroid_jetpack.ui.data

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel

/**
 * @author by zhengshaorui 2022/1/24
 * describe：
 */
class MainViewModel : ViewModel() {
    val openDrawer = ObservableBoolean(false)

}