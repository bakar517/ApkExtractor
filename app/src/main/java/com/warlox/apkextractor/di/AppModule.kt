package com.warlox.apkextractor.di

import android.app.Application
import android.content.Context
import com.warlox.apkextractor.repo.AppsProvider
import com.warlox.apkextractor.repo.AppsProviderImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    fun getAppsProvider(appsProviderImp: AppsProviderImp): AppsProvider = appsProviderImp
}