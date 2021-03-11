package com.warlox.apkextractor.view.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.warlox.apkextractor.data.model.ApplicationModel
import com.warlox.apkextractor.util.ApplicationUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class HomeScreenViewModel(application: Application) : AndroidViewModel(application){

    private val compositeDisposable = CompositeDisposable()

    private val _listStatus = MutableLiveData<String>()
    val listStatus:LiveData<String>
        get() = _listStatus

    private val _isListItemAtLeastOne = MutableLiveData<Boolean>()
    val isListItemAtLeastOne:LiveData<Boolean>
        get() = _isListItemAtLeastOne


    private val _applicationModelList = MutableLiveData<List<ApplicationModel>>()
    val applicationModelList:LiveData<List<ApplicationModel>>
        get() = _applicationModelList

    private val _isApplicationLoading = MutableLiveData<Boolean>()
    val isApplicationLoading:LiveData<Boolean>
        get() = _isApplicationLoading

    private var applicationContext: Context = application.applicationContext

    init {
        loadApplications()
    }

    private fun loadApplications(){
        _isApplicationLoading.value = true

        val disposable = ApplicationUtil.getListOfUserInstalledApplication(applicationContext)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _isApplicationLoading.value = false
                _applicationModelList.value = it
            },{
                _isApplicationLoading.value = false
            })
        compositeDisposable.add(disposable)

    }

    override fun onCleared() {

        compositeDisposable.clear()
        super.onCleared()
    }
}
