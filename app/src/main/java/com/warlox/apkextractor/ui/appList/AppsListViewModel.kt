package com.warlox.apkextractor.ui.appList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.warlox.apkextractor.data.model.ApplicationModel
import com.warlox.apkextractor.repo.AppsProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class AppsListViewModel @Inject constructor(private val appsProvider: AppsProvider)
    : ViewModel() {

    private val _listStatus = MutableLiveData<String>()
    val listStatus: LiveData<String>
        get() = _listStatus

    private val _isListItemAtLeastOne = MutableLiveData<Boolean>()
    val isListItemAtLeastOne: LiveData<Boolean>
        get() = _isListItemAtLeastOne


    private val _applicationModelList = MutableLiveData<List<ApplicationModel>>()

    val applicationModelList: LiveData<List<ApplicationModel>>
        get() = _applicationModelList

    private val _isApplicationLoading = MutableLiveData<Boolean>()
    val isApplicationLoading: LiveData<Boolean>
        get() = _isApplicationLoading


    init {
        loadApplications()
    }

    private fun loadApplications() {
        _isApplicationLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            _applicationModelList.postValue(appsProvider.getListOfAllApplication())
            _isApplicationLoading.postValue(false)
        }

    }

}
