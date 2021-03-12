package com.warlox.apkextractor.di

import androidx.lifecycle.ViewModelProvider
import com.warlox.apkextractor.viewmodel.AppExtractorViewModelFactory
import dagger.Binds
import dagger.Module


@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: AppExtractorViewModelFactory):
            ViewModelProvider.Factory

}