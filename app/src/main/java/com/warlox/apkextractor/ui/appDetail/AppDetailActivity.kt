package com.warlox.apkextractor.ui.appDetail

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.warlox.apkextractor.BR
import com.warlox.apkextractor.R
import com.warlox.apkextractor.databinding.ActivityAppDetailBinding
import com.warlox.apkextractor.ui.appDetail.di.ExtraParamsViewModelFactory
import com.warlox.apkextractor.ui.base.BaseActivity
import com.warlox.apkextractor.util.ApplicationUtil
import com.warlox.apkextractor.util.extension.copyTextToClip
import com.warlox.apkextractor.util.extension.goToSetting


class AppDetailActivity : BaseActivity<ActivityAppDetailBinding, AppDetailViewModel>() {

    companion object {
        const val EXTRAS_PACKAGE_NAME = "package_name"
    }

    private lateinit var viewModel: AppDetailViewModel

    override fun getLayoutId() = R.layout.activity_app_detail

    override fun getViewModel(): AppDetailViewModel {
        val factory = ExtraParamsViewModelFactory(this.packageManager, getAppPackageName())
        viewModel = ViewModelProvider(this, factory).get(AppDetailViewModel::class.java)
        return viewModel
    }

    override fun getBindingVariable() = BR.viewHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeViewModelProperties()
    }

    private fun getAppPackageName(): String {
        return intent.getStringExtra(EXTRAS_PACKAGE_NAME)!!
    }

    private fun observeViewModelProperties() {
        viewModel.onLaunchApplication().observe(this, {
            val intent = packageManager.getLaunchIntentForPackage(viewModel.applicationInfo.packageName)
            startActivity(intent)
        })

        viewModel.onGoToAppSetting().observe(this, {
            goToSetting(viewModel.applicationInfo.packageName)
        })
        viewModel.onCopyToClipboardClick().observe(this, {
            val value = ApplicationUtil.getApplicationInfoForClip(applicationContext, viewModel.applicationInfo)
            copyTextToClip(value, showToast = true)
        })

        viewModel.onShareClick().observe(this, {

        })

    }
}