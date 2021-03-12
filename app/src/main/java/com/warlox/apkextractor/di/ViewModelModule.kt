package com.warlox.apkextractor.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.warlox.apkextractor.ui.appList.AppsListViewModel
import com.warlox.apkextractor.viewmodel.AppExtractorViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AppsListViewModel::class)
    abstract fun bindAppsListViewModel(appsListViewModel: AppsListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppExtractorViewModelFactory):
            ViewModelProvider.Factory

}