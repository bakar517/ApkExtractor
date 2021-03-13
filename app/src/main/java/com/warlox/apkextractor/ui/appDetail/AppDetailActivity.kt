package com.warlox.apkextractor.ui.appDetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import com.warlox.apkextractor.BR
import com.warlox.apkextractor.R
import com.warlox.apkextractor.databinding.ActivityAppDetailBinding
import com.warlox.apkextractor.ui.appDetail.di.ExtraParamsViewModelFactory
import com.warlox.apkextractor.ui.base.BaseActivity
import com.warlox.apkextractor.util.ApplicationUtil
import com.warlox.apkextractor.util.ClipBoardManager
import com.warlox.apkextractor.util.StringUtil


class AppDetailActivity : BaseActivity<ActivityAppDetailBinding, AppDetailViewModel>() {

    companion object {
        const val EXTRAS_PACKAGE_NAME = "package_name"

        fun getStarterIntent(context: Context, packageName: String): Intent {
            val intent = Intent(context, AppDetailActivity::class.java)
            intent.putExtra(EXTRAS_PACKAGE_NAME, packageName)
            return intent
        }
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
        viewModel.launchApplication.observe(this, {
            val intent = packageManager.getLaunchIntentForPackage(viewModel.applicationInfo.packageName)
            startActivity(intent)
        })

        viewModel.goToAppSetting.observe(this, {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = Uri.fromParts("package", viewModel.applicationInfo.packageName, null)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        })
        viewModel.copyToClipboard.observe(this, {
            val value = ApplicationUtil.getApplicationInfoForClip(applicationContext, viewModel.applicationInfo)
            ClipBoardManager.copyToClipboard(applicationContext, value)
            showToast(R.string.copied_to_clipboard)
        })

        viewModel.shareApplication.observe(this, {

        })

    }

    private fun showToast(@StringRes id: Int) {
        showToast(StringUtil.getStringFromRes(applicationContext, id))
    }

    private fun showToast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }

}