package com.warlox.apkextractor.ui.setting

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.warlox.apkextractor.BR
import com.warlox.apkextractor.R
import com.warlox.apkextractor.databinding.ActivitySettingBinding
import com.warlox.apkextractor.ui.base.BaseActivity
import javax.inject.Inject

class SettingActivity : BaseActivity<ActivitySettingBinding, SettingViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SettingViewModel


    override fun getLayoutId() = R.layout.activity_setting

    override fun getViewModel(): SettingViewModel {
        viewModel = ViewModelProvider(this, viewModelFactory).get(SettingViewModel::class.java)
        return viewModel
    }

    override fun getBindingVariable() = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)
    }

}