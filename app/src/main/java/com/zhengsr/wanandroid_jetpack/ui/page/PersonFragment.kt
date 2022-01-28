package com.zhengsr.wanandroid_jetpack.ui.page

import com.kunminx.architecture.ui.page.DataBindingConfig
import com.zhengsr.wanandroid_jetpack.BR
import com.zhengsr.wanandroid_jetpack.R
import com.zhengsr.wanandroid_jetpack.ui.base.BaseFragment
import com.zhengsr.wanandroid_jetpack.ui.data.PersonViewModel


class PersonFragment : BaseFragment<PersonViewModel>() {

    override fun initViewModel() {
        state = getViewModel(PersonViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
         return DataBindingConfig(R.layout.fragment_person,BR.vm,state)
    }




}