package com.zhengsr.wanandroid_jetpack.databindadapter

import androidx.core.view.GravityCompat
import androidx.databinding.BindingAdapter
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.SimpleDrawerListener

/**
 * @author by zhengshaorui 2022/1/24
 * describe：侧边抽屉
 */
object DrawerDatabindAdapter  {

    @JvmStatic
    @BindingAdapter(value = ["isOpenDrawer"], requireAll = false)
    fun openDrawer(drawerLayout: DrawerLayout, isOpenDrawer: Boolean) {
        if (isOpenDrawer && !drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.openDrawer(GravityCompat.START)
        } else {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["bindDrawerListener"], requireAll = false)
    fun listenDrawerState(drawerLayout: DrawerLayout, listener: SimpleDrawerListener?) {
        drawerLayout.addDrawerListener(listener!!)
    }
}