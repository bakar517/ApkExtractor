package com.warlox.apkextractor.di

import com.warlox.apkextractor.ui.appList.AppsListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeAppsListActivity(): AppsListActivity

}
