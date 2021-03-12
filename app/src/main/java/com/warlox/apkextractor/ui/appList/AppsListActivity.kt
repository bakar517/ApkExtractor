package com.warlox.apkextractor.ui.appList;

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.warlox.apkextractor.R
import com.warlox.apkextractor.data.model.ApplicationModel
import com.warlox.apkextractor.databinding.ActivityAppsListBinding
import com.warlox.apkextractor.view.MyViewModelFactory
import com.warlox.apkextractor.view.adapter.ApplicationListAdapter
import com.warlox.apkextractor.view.callback.ApplicationRecycleViewItemClick
import com.warlox.apkextractor.ui.appDetail.AppDetailActivity
import com.warlox.apkextractor.ui.setting.SettingScreenActivity

class AppsListActivity : AppCompatActivity(), ApplicationRecycleViewItemClick {

    lateinit var binding: ActivityAppsListBinding

    lateinit var applicationListAdapter: ApplicationListAdapter

    lateinit var viewModel: AppsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_apps_list)
        setSupportActionBar(binding.toolbar)

        setUpViewModel()

        initAdapter()
        setUpAdapter()
        startObservingForProgress()
    }

    private fun setUpViewModel() {
        val factory = MyViewModelFactory(application)
//        viewModel = ViewModelProviders.of(this).get(HomeScreenViewModel::class.java)
        viewModel = ViewModelProviders.of(this, factory).get(AppsListViewModel::class.java)
        binding.viewHolder = viewModel
        binding.lifecycleOwner = this

    }

    private fun initAdapter() {
        applicationListAdapter = ApplicationListAdapter(this)
    }

    private fun setUpAdapter() {
        binding.applicationList.adapter = applicationListAdapter

        viewModel.applicationModelList.observe(this, Observer {
            applicationListAdapter.setApplicationList(it)
        })

    }

    private fun startObservingForProgress() {
        viewModel.isApplicationLoading.observe(this, Observer {
            if (it) {
                binding.shimmerFrameLayout.startShimmer()
            } else {
                binding.shimmerFrameLayout.startShimmer()
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) {
            startActivity(Intent(this, SettingScreenActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }

    }

    override fun onApplicationListItemClick(applicationModel: ApplicationModel) {
        val intent = AppDetailActivity.getStarterIntent(this@AppsListActivity, applicationModel.appBundleId)
        startActivity(intent)
    }

}
