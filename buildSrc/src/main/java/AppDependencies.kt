import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {

    //std lib
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    //android ui
    private val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    private const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    private const val cardView = "androidx.cardview:cardview:${Versions.cardView}"

    private val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"

    //test libs
    private val junit = "junit:junit:${Versions.junit}"
    private val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    private const val leakCanary =
        "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    // ViewModel
    private const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"

    //LiveData
    private const val lifecycleLiveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
    private const val lifecycleViewModelSavedState =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle_version}"
    private const val lifecycleCompiler =
        "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle_version}"
    private const val lifecycleTestVersion =
        "androidx.arch.core:core-testing:${Versions.arch_version}"


    private const val shimmer =
        "com.facebook.shimmer:shimmer:${Versions.shimmer}"

    private const val rxAndroid =
        "io.reactivex.rxjava3:rxandroid:${Versions.rxAndroid}"

    private const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    private const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    private const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    private const val daggerAndroidSupport =
        "com.google.dagger:dagger-android-support:${Versions.dagger}"
    private const val daggerSupportCompiler =
        "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    private const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    private const val intuit = "com.intuit.sdp:sdp-android:${Versions.intuit}"

    private const val dexter = "com.karumi:dexter:${Versions.dexter}"

    val appLibraries = arrayListOf<String>().apply {
        add(kotlinStdLib)
        add(appcompat)
        add(coreKtx)
        add(constraintLayout)
        add(materialDesign)
        add(cardView)
        add(recyclerview)
        add(dagger)
        add(shimmer)
        add(rxAndroid)
        add(daggerAndroid)
        add(daggerAndroidSupport)
        add(timber)
        add(intuit)
        add(dexter)
        add(lifecycleViewModelSavedState)
        add(lifecycleLiveData)
        add(lifecycleViewModel)
    }

    val kapt = arrayListOf<String>().apply {
        add(lifecycleCompiler)
        add(daggerCompiler)
        add(daggerSupportCompiler)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
        add(lifecycleTestVersion)
    }

    val debugImplementation = arrayListOf<String>().apply {
        add(leakCanary)
    }

}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.debugImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("debugImplementation", dependency)
    }
}