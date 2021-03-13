package com.warlox.apkextractor.di

import com.warlox.apkextractor.ui.appDetail.AppDetailActivity
import com.warlox.apkextractor.ui.appList.AppsListActivity
import com.warlox.apkextractor.ui.setting.SettingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeAppsListActivity(): AppsListActivity

    @ContributesAndroidInjector
    abstract fun contributeSettingActivity(): SettingActivity

    @ContributesAndroidInjector
    abstract fun contributeAppDetailActivity(): AppDetailActivity

}
