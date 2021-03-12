package com.warlox.apkextractor.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.warlox.apkextractor.R
import com.warlox.apkextractor.databinding.ActivitySettingScreenBinding
import com.warlox.apkextractor.view.viewmodel.SettingScreenViewModel

class SettingScreenActivity:AppCompatActivity() {
    lateinit var binding:ActivitySettingScreenBinding
    lateinit var viewModel:SettingScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting_screen)

        setSupportActionBar(binding.toolbar)

        setUpViewModel()
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(this).get(SettingScreenViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

    }

}