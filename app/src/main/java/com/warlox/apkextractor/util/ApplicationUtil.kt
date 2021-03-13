package com.warlox.apkextractor.util

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.pm.PackageInfoCompat
import com.warlox.apkextractor.R
import com.warlox.apkextractor.data.model.ApplicationModel

class ApplicationUtil private constructor() {
    companion object {

        fun getListOfUserInstalledApplication(context: Context): List<ApplicationModel> {
            return getAllApplicationList(context, false)
        }

        private fun getAllApplicationList(context: Context, includeSystemApps: Boolean): List<ApplicationModel> {

            val list = mutableListOf<ApplicationModel>()
            val packageManager = context.packageManager
            val installedPackages = packageManager.getInstalledPackages(PackageManager.GET_META_DATA)

            for (packageInfo in installedPackages) {
                try {
                    val applicationInfo: ApplicationInfo? = packageInfo.applicationInfo
                    if (applicationInfo != null) {
                        val isSystemApp = applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM !== 0
                        if (!includeSystemApps and isSystemApp){
                            continue
                        }
                        val appName = getAppName(packageManager, applicationInfo)
                        val packageName = packageInfo.packageName
                        val icon = packageManager.getApplicationIcon(packageInfo.packageName)
                        val versionName = packageInfo.versionName
                        val versionCode: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            val longVersionCode = PackageInfoCompat.getLongVersionCode(packageInfo)
                            longVersionCode.toInt() // avoid huge version numbers and you will be ok
                        } else {
                            packageInfo.versionCode
                        }

                        val application = ApplicationModel(appName, packageName, icon,
                                versionName, versionCode,
                                isSystemApp)
                        list.add(application)
                    }
                } catch (nameMissing: PackageManager.NameNotFoundException) {
                    nameMissing.printStackTrace()
                }
            }
            return list
        }

        fun getPackageInfo(packageManager: PackageManager, packageName:String, flag:Int): PackageInfo? {
            return packageManager.getPackageInfo(packageName, flag)
        }

        fun getAppName(packageManager: PackageManager, applicationInfo: ApplicationInfo):String{
            return packageManager.getApplicationLabel(applicationInfo) as String
        }
        fun getAppVersionCode(packageInfo: PackageInfo):Int{
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val longVersionCode = PackageInfoCompat.getLongVersionCode(packageInfo)
                longVersionCode.toInt() // avoid huge version numbers and you will be ok
            } else {
                packageInfo.versionCode
            }
        }
        fun isLargeHeap(applicationInfo: ApplicationInfo): Boolean {
            return applicationInfo.flags and ApplicationInfo.FLAG_LARGE_HEAP != 0
        }

        fun isHardwareAccelerated(applicationInfo: ApplicationInfo): Boolean {
            return applicationInfo.flags and ApplicationInfo.FLAG_HARDWARE_ACCELERATED != 0
        }

        fun isBackupAllowed(applicationInfo: ApplicationInfo): Boolean {
            return applicationInfo.flags and ApplicationInfo.FLAG_ALLOW_BACKUP != 0
        }

        fun isRLTSupport(applicationInfo: ApplicationInfo): Boolean {
            return applicationInfo.flags and ApplicationInfo.FLAG_SUPPORTS_RTL != 0
        }

        fun isDebugBuild(applicationInfo: ApplicationInfo): Boolean {
            return applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
        }

        internal fun getApplicationInfoForClip(context: Context, applicationInfo: ApplicationInfo):String{
            val builder = StringBuilder()
            val _applicationName = getAppName(context.packageManager, applicationInfo)
            val applicationName = StringUtil.getStringFromRes(context, R.string.name)+": "+_applicationName
            StringUtil.appendWithNewLine(builder, applicationName)
            val applicationPackage = StringUtil.getStringFromRes(context, R.string._package)+": "+applicationInfo.packageName
            StringUtil.appendWithNewLine(builder, applicationPackage)

            val packageInfo = getPackageInfo(context.packageManager, applicationInfo.packageName, PackageManager.GET_META_DATA)
            val applicationVersionName = StringUtil.getStringFromRes(context, R.string.version_name)+": "+packageInfo!!.versionName
            StringUtil.appendWithNewLine(builder, applicationVersionName)

            val _applicationVersionCode = getAppVersionCode(packageInfo)
            val applicationVersionCode = StringUtil.getStringFromRes(context, R.string.version_code)+": "+_applicationVersionCode
            StringUtil.appendWithNewLine(builder, applicationVersionCode)
            return builder.toString()
        }

    }
}