package com.warlox.apkextractor.ui.appDetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.warlox.apkextractor.R
import com.warlox.apkextractor.databinding.ActivityAppDetailBinding
import com.warlox.apkextractor.util.ApplicationUtil
import com.warlox.apkextractor.util.ClipBoardManager
import com.warlox.apkextractor.util.StringUtil
import com.warlox.apkextractor.view.MyViewModelFactory


class AppDetailActivity : AppCompatActivity() {

    companion object{
        private const val EXTRAS_PACKAGE_NAME = "package_name"

        fun getStarterIntent(context: Context, packageName:String): Intent {
            val intent = Intent(context, AppDetailActivity::class.java)
            intent.putExtra(EXTRAS_PACKAGE_NAME, packageName)
            return intent
        }
    }

    lateinit var binding:ActivityAppDetailBinding

    lateinit var viewModel: AppDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_app_detail)
        setSupportActionBar(binding.toolbar)

        val packageName = intent.getStringExtra(EXTRAS_PACKAGE_NAME)!!

        setUpViewModel(packageName)

        observeViewModelProperties()
    }

    private fun setUpViewModel(packageName:String) {
        val applicationInfo = this.packageManager.getApplicationInfo(packageName, 0)
        val factory = MyViewModelFactory(application,applicationInfo)
        viewModel = ViewModelProviders.of(this, factory).get(AppDetailViewModel::class.java)
        binding.viewHolder = viewModel
        binding.lifecycleOwner = this

    }

    private fun observeViewModelProperties() {
        viewModel.launchApplication.observe(this, Observer {
            val intent = packageManager.getLaunchIntentForPackage(viewModel.applicationInfo.packageName)
            startActivity(intent)
        })

        viewModel.goToAppSetting.observe(this, Observer {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = Uri.fromParts("package", viewModel.applicationInfo.packageName,null)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        })
        viewModel.copyToClipboard.observe(this, Observer {
            val value = ApplicationUtil.getApplicationInfoForClip(applicationContext, viewModel.applicationInfo)
            ClipBoardManager.copyToClipboard(applicationContext,value)
            showToast(R.string.copied_to_clipboard)
        })

        viewModel.shareApplication.observe(this, Observer {

        })

    }

    private fun showToast(@StringRes id:Int){
        showToast(StringUtil.getStringFromRes(applicationContext,id))
    }

    private fun showToast(text:String){
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }

}