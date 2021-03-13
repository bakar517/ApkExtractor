package com.warlox.apkextractor.ui.appList

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.warlox.apkextractor.BR
import com.warlox.apkextractor.R
import com.warlox.apkextractor.data.model.ApplicationModel
import com.warlox.apkextractor.databinding.ActivityAppsListBinding
import com.warlox.apkextractor.ui.appDetail.AppDetailActivity
import com.warlox.apkextractor.ui.appList.adapter.ApplicationListAdapter
import com.warlox.apkextractor.ui.base.BaseActivity
import com.warlox.apkextractor.ui.setting.SettingActivity
import com.warlox.apkextractor.util.IntentParams
import com.warlox.apkextractor.util.extension.navigate
import javax.inject.Inject


class AppsListActivity : BaseActivity<ActivityAppsListBinding, AppsListViewModel>(),
        ApplicationRecycleViewItemClick {

    override fun getLayoutId() = R.layout.activity_apps_list

    override fun getViewModel(): AppsListViewModel {
        viewModel = ViewModelProvider(this, viewModelFactory).get(AppsListViewModel::class.java)
        return viewModel
    }

    override fun getBindingVariable() = BR.viewHolder

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: AppsListViewModel

    @Inject
    lateinit var applicationListAdapter: ApplicationListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpAdapter()
        startObservingForProgress()
    }

    private fun setUpAdapter() {
        binding.applicationList.apply {
            layoutManager = LinearLayoutManager(this@AppsListActivity)
            addItemDecoration(DividerItemDecoration(this@AppsListActivity,
                    DividerItemDecoration.VERTICAL))
            adapter = applicationListAdapter
        }
        viewModel.applicationModelList.observe(this, {
            applicationListAdapter.submitList(it)
        })

    }

    private fun startObservingForProgress() {
        viewModel.isApplicationLoading.observe(this, {
            if (it) {
                binding.shimmerFrameLayout.startShimmer()
            } else {
                binding.shimmerFrameLayout.stopShimmer()
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) {
            startActivity(Intent(this, SettingActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(applicationModel: ApplicationModel) {
        navigate<AppDetailActivity>(
                listOf(
                        IntentParams(
                                key = AppDetailActivity.EXTRAS_PACKAGE_NAME,
                                value = applicationModel.appBundleId)))
    }
}
