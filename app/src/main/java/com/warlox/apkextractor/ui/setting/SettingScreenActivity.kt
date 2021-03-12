package com.warlox.apkextractor.ui.setting

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.warlox.apkextractor.BR
import com.warlox.apkextractor.R
import com.warlox.apkextractor.databinding.ActivitySettingScreenBinding
import com.warlox.apkextractor.ui.base.BaseActivity
import javax.inject.Inject

class SettingScreenActivity : BaseActivity<ActivitySettingScreenBinding, SettingScreenViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SettingScreenViewModel


    override fun getLayoutId() = R.layout.activity_setting_screen

    override fun getViewModel(): SettingScreenViewModel {
        viewModel = ViewModelProvider(this, viewModelFactory).get(SettingScreenViewModel::class.java)
        return viewModel
    }

    override fun getBindingVariable() = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)
    }

}