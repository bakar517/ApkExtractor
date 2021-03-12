package com.warlox.apkextractor.ui.appList;

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.warlox.apkextractor.R;
import com.warlox.apkextractor.data.model.ApplicationModel
import com.warlox.apkextractor.databinding.ActivityHomeScreenBinding
import com.warlox.apkextractor.view.MyViewModelFactory
import com.warlox.apkextractor.view.adapter.ApplicationListAdapter
import com.warlox.apkextractor.view.callback.ApplicationRecycleViewItemClick
import com.warlox.apkextractor.view.ui.activities.AppDetailActivity
import com.warlox.apkextractor.view.ui.activities.SettingScreenActivity

class HomeScreenActivity: AppCompatActivity(), ApplicationRecycleViewItemClick {

    lateinit var binding:ActivityHomeScreenBinding

    lateinit var applicationListAdapter: ApplicationListAdapter

    lateinit var viewModel: HomeScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_screen)
        setSupportActionBar(binding.toolbar)

        setUpViewModel()

        initAdapter()
        setUpAdapter()
        startObservingForProgress()
    }

    private fun setUpViewModel() {
        val factory = MyViewModelFactory(application)
//        viewModel = ViewModelProviders.of(this).get(HomeScreenViewModel::class.java)
        viewModel = ViewModelProviders.of(this, factory).get(HomeScreenViewModel::class.java)
        binding.viewHolder = viewModel
        binding.lifecycleOwner = this

    }

    private fun initAdapter() {
        applicationListAdapter = ApplicationListAdapter(this)
    }

    private fun setUpAdapter(){
        binding.applicationList.adapter = applicationListAdapter

        viewModel.applicationModelList.observe(this, Observer {
            applicationListAdapter.setApplicationList(it)
        })

    }

    private fun startObservingForProgress(){
        viewModel.isApplicationLoading.observe(this, Observer {
            if (it){
                binding.shimmerFrameLayout.startShimmer()
            }else{
                binding.shimmerFrameLayout.startShimmer()
            }
        })

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings){
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
        val intent = AppDetailActivity.getStarterIntent(this@HomeScreenActivity, applicationModel.appBundleId)
        startActivity(intent)
    }

}
