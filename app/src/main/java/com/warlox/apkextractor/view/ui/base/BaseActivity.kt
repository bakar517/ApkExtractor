package com.warlox.apkextractor.view.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel

abstract class BaseActivity<T: BaseViewModel>: AppCompatActivity(){

    lateinit var viewModel: T
    protected abstract fun createViewModel():T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
    }

}