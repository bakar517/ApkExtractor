package com.warlox.apkextractor.ui.appDetail

import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.warlox.apkextractor.ui.SingleMutableLiveEvent
import com.warlox.apkextractor.util.ApplicationUtil
import com.warlox.apkextractor.util.DateTimeUtil
import java.io.File
import javax.inject.Inject


class AppDetailViewModel @Inject constructor(
        private val packageManager: PackageManager,
        private val packageName: String) : ViewModel() {

    val applicationInfo = packageManager.getApplicationInfo(packageName, 0)

    private val _lastModificationTimeOfApp = MutableLiveData<String>()
    val lastModificationTimeOfApp: LiveData<String>
        get() = _lastModificationTimeOfApp

    private val _appInstallationTime = MutableLiveData<String>()
    val appInstallationTime: LiveData<String>
        get() = _appInstallationTime

    private val _appIconDrawable = MutableLiveData<Drawable>()
    val appIconDrawable :LiveData<Drawable>
        get() = _appIconDrawable

    private val _appName = MutableLiveData<String>()
    val appName :LiveData<String>
        get() = _appName

    private val _appVersionName = MutableLiveData<String>()
    val appVersionName:LiveData<String>
        get() = _appVersionName

    private val _appVersionCode = MutableLiveData<String>()
    val appVersionCode:LiveData<String>
        get() = _appVersionCode

    private val _appInstallerVendor = MutableLiveData<String>()
    val appInstallerVendor:LiveData<String>
        get() = _appInstallerVendor

    private val _appTargetVersion = MutableLiveData<String>()
    val appTargetVersion:LiveData<String>
        get() = _appTargetVersion

    private val _appMinVersion = MutableLiveData<String>()
    val appMinVersion:LiveData<String>
        get() = _appMinVersion

    private val _appSignature = MutableLiveData<String>()
    val appSignature:LiveData<String>
        get() = _appSignature


    private val _appMetaData = MutableLiveData<MutableList<String>>()
    val appMetaData:LiveData<MutableList<String>>
        get() = _appMetaData

    private val _appPermissions = MutableLiveData<MutableList<String>>()
    val appPermissions:LiveData<MutableList<String>>
        get() = _appPermissions

    private val _appActivities = MutableLiveData<MutableList<String>>()
    val appActivities:LiveData<MutableList<String>>
    get() = _appActivities

    private val _appProviders = MutableLiveData<MutableList<String>>()
    val appProviders:LiveData<MutableList<String>>
        get() = _appProviders

    private val _appReceivers = MutableLiveData<MutableList<String>>()
    val appReceivers:LiveData<MutableList<String>>
        get() = _appReceivers

    private val _appDirectories = MutableLiveData<MutableList<String>>()
    val appDirectories:LiveData<MutableList<String>>
        get() = _appDirectories

    private val _appSharedLibraries = MutableLiveData<MutableList<String>>()
    val appSharedLibraries:LiveData<MutableList<String>>
        get() = _appSharedLibraries

    private val _appNativeLibraries = MutableLiveData<MutableList<String>>()
    val appNativeLibraries: LiveData<MutableList<String>>
        get() = _appNativeLibraries

    private val _appOtherProperties = MutableLiveData<MutableList<String>>()
    val appOtherProperties: LiveData<MutableList<String>>
        get() = _appOtherProperties

    private val onCopyToClipboard = SingleMutableLiveEvent<Boolean>()
    private val onLaunchApplication = SingleMutableLiveEvent<Boolean>()
    private val goToAppSetting = SingleMutableLiveEvent<Boolean>()
    private val onAppShare = SingleMutableLiveEvent<Boolean>()


    internal fun onCopyToClipboardClick() = onCopyToClipboard
    internal fun onLaunchApplication() = onLaunchApplication
    internal fun onGoToAppSetting() = goToAppSetting
    internal fun onShareClick() = onAppShare

    init {
        loadBasicPropertiesOfApp()
        loadSDKInfoOfApp()
        loadSignatureInfoOfApp()

        loadAllMetaDataOfApp()
        loadAllPermissionsOfApp()
        loadAllActivitiesOfApp()
        loadAllProvidersOfApp()
        loadAllReceiversOfApp()
        loadAllDirectoriesOfApp()
        loadAllSharedLibrariesOfApp()
        loadAllNativeLibrariesOfApp()
        loadAllOtherPropertiesOfApp()
    }


    private fun loadBasicPropertiesOfApp() {
        val packageInfo = ApplicationUtil.getPackageInfo(packageManager, applicationInfo.packageName,
            PackageManager.GET_META_DATA)
        _appName.value = ApplicationUtil.getAppName(packageManager,applicationInfo)
        _appIconDrawable.value = packageManager.getApplicationIcon(applicationInfo.packageName)
        _appVersionName.value = packageInfo!!.versionName
        _appVersionCode.value = ApplicationUtil.getAppVersionCode(packageInfo).toString()
        val installVendor = packageManager.getInstallerPackageName(applicationInfo.packageName)
        installVendor?.let { _appInstallerVendor.value = it }
        Log.v("installer_tag","getInstallerPackageName ${packageManager.getInstallerPackageName(applicationInfo.packageName)}")

    }

    private fun loadSDKInfoOfApp() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            _appMinVersion.value = applicationInfo.minSdkVersion.toString()
        }
        _appTargetVersion.value = applicationInfo.targetSdkVersion.toString()
    }

    private fun loadSignatureInfoOfApp() {
        val packageInfo = ApplicationUtil.getPackageInfo(packageManager, applicationInfo.packageName,
                PackageManager.GET_SIGNATURES)

        _appInstallationTime.value = DateTimeUtil.getFormattedTime(packageInfo!!.firstInstallTime)
            _lastModificationTimeOfApp.value = DateTimeUtil.getFormattedTime(packageInfo.lastUpdateTime)

    }

    private fun loadAllMetaDataOfApp(){
        val applicationInfo = packageManager.getApplicationInfo(this.applicationInfo.packageName, PackageManager.GET_META_DATA)
        val metaData = applicationInfo.metaData
        val metaDataList = mutableListOf<String>()
        metaData?.let { bundle ->
            for (key in bundle.keySet()) {
                val value = bundle.get(key)
                value?.let {
                    metaDataList.add(it.toString())
                }
                Log.v("meta_data_tag", key + " : " + if (bundle.get(key) != null) bundle.get(key) else "NULL")
            }
            _appMetaData.value = metaDataList
        }
    }

    private fun loadAllPermissionsOfApp(){
        val packageInfo = ApplicationUtil.getPackageInfo(packageManager, applicationInfo.packageName, PackageManager.GET_PERMISSIONS)
        packageInfo?.let {
            val permissionList = mutableListOf<String>()
            if (!it.requestedPermissions.isNullOrEmpty()){
                for (permission in it.requestedPermissions){
                    Log.v("permission_logs","permission => $permission")
                    permissionList.add(permission)
                }
            }
            _appPermissions.value = permissionList
        }
    }

    private fun loadAllActivitiesOfApp(){
        val packageInfo = ApplicationUtil.getPackageInfo(packageManager, applicationInfo.packageName,
            PackageManager.GET_ACTIVITIES)
        val activitiesList = mutableListOf<String>()
        packageInfo?.let { _packageInfo ->
            _packageInfo.activities?.let {
                for (providerInfo in it) { // Dump permission info
                    activitiesList.add(providerInfo.name)
                }
            }
        }
        _appActivities.value = activitiesList
    }

    private fun loadAllProvidersOfApp(){
        val packageInfo = ApplicationUtil.getPackageInfo(packageManager, applicationInfo.packageName,
            PackageManager.GET_PROVIDERS)
        val providerList = mutableListOf<String>()
        packageInfo?.let { _packageInfo ->
            _packageInfo.providers?.let {
                for (providerInfo in it) { // Dump providers info
                    providerList.add(providerInfo.name)
                }
            }
        }
        _appProviders.value = providerList
    }

    private fun loadAllReceiversOfApp(){
        val packageInfo = ApplicationUtil.getPackageInfo(packageManager, applicationInfo.packageName,
            PackageManager.GET_RECEIVERS)
        val receiverList = mutableListOf<String>()
        packageInfo?.let { _packageInfo ->
            _packageInfo.receivers?.let {
                for (receiverInfo in it) { // Dump Receivers info
                    receiverList.add(receiverInfo.name)
                }
            }
        }
        _appReceivers.value = receiverList
    }

    private fun loadAllDirectoriesOfApp(){
        val directoryList = mutableListOf<String>()
        directoryList.add(applicationInfo.dataDir)
        directoryList.add(applicationInfo.nativeLibraryDir)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            directoryList.add(applicationInfo.deviceProtectedDataDir)
        }
        _appDirectories.value = directoryList
    }

    private fun loadAllSharedLibrariesOfApp(){
        val sharedLibraryList = mutableListOf<String>()
        if (!applicationInfo.sharedLibraryFiles.isNullOrEmpty()){
            for (sharedLibraryName in applicationInfo.sharedLibraryFiles) { // Dump Receivers info
                sharedLibraryList.add(sharedLibraryName)
            }
        }
        _appSharedLibraries.value = sharedLibraryList
    }

    private fun loadAllNativeLibrariesOfApp(){
        Log.v("native_lib_log","path => ${applicationInfo.nativeLibraryDir}")
        val nativeLibraryPath = File(applicationInfo.nativeLibraryDir)
        nativeLibraryPath.let {
            if (nativeLibraryPath.exists()){
                val list = nativeLibraryPath.listFiles()
                val listOfNativeLibraries = mutableListOf<String>()
                if (!list.isNullOrEmpty()){
                    Log.v("native_lib_log","list => ${list.size}")
                    for (file in list){
                        Log.v("native_lib_log","file name => ${file.name}")
                        Log.v("native_lib_log","file path => ${file.path}")
                        Log.v("native_lib_log","file absolutePath => ${file.absolutePath}")
                        listOfNativeLibraries.add(file.absolutePath)
                    }
                }else{
                    Log.v("native_lib_log","list is null")

                }
                _appNativeLibraries.value = listOfNativeLibraries
            }else{
                Log.v("native_lib_log","native lib file not exist")
            }

        }
    }

    private fun loadAllOtherPropertiesOfApp() {
        val isLargeHeap = ApplicationUtil.isLargeHeap(applicationInfo)
        val isHardwareAccelerated = ApplicationUtil.isHardwareAccelerated(applicationInfo)
        val isBackupAllowed = ApplicationUtil.isBackupAllowed(applicationInfo)
        val isRLTSupport = ApplicationUtil.isRLTSupport(applicationInfo)
    }

    fun onLaunchApplicationClick() {
        onLaunchApplication.value = true
    }

    fun onAppSettingClick() {
        goToAppSetting.value = true

    }

    fun onCopyApplicationDetailClick() {
        onCopyToClipboard.value = true
    }

    fun onAppShareClick() {
        onAppShare.value = true

    }
}