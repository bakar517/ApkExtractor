package com.warlox.apkextractor.view.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment<T: BaseViewModel>: Fragment() {

    lateinit var viewModel: T
    protected abstract fun provideViewModel():T
    protected abstract fun provideViewOfFragment(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = provideViewModel()
        return provideViewOfFragment(inflater, container, savedInstanceState)
    }


}