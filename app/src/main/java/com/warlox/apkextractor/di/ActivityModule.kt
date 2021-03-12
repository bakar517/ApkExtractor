package com.warlox.apkextractor.di

import com.warlox.apkextractor.ui.appList.AppsListActivity
import com.warlox.apkextractor.ui.setting.SettingScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeAppsListActivity(): AppsListActivity

    @ContributesAndroidInjector
    abstract fun contributeSettingScreenActivity(): SettingScreenActivity

}
