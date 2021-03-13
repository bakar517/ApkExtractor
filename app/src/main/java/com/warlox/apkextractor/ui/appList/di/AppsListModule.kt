package com.warlox.apkextractor.ui.appList.di

import com.warlox.apkextractor.ui.appList.ApplicationRecycleViewItemClick
import com.warlox.apkextractor.ui.appList.AppsListActivity
import com.warlox.apkextractor.ui.appList.adapter.ApplicationListAdapter
import dagger.Module
import dagger.Provides

@Module
class AppsListModule {

    @Provides
    fun provideAdapter(listener: ApplicationRecycleViewItemClick) =
            ApplicationListAdapter(listener)

    @Provides
    fun provideListener(appsListActivity: AppsListActivity): ApplicationRecycleViewItemClick {
        return appsListActivity
    }
}