package com.warlox.apkextractor.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import dagger.android.AndroidInjection


abstract class BaseActivity<T : ViewDataBinding, V : ViewModel> : AppCompatActivity() {
    lateinit var binding: T
        private set

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * @return view model
     */
    abstract fun getViewModel(): V


    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Int


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        dataBinding()
    }

    private fun dataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this
        binding.setVariable(getBindingVariable(), getViewModel())
        binding.executePendingBindings()
    }


}