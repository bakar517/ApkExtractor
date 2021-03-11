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
    private const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"

    private val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"

    //test libs
    private val junit = "junit:junit:${Versions.junit}"
    private val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    //glide
    private const val glideBase = "com.github.bumptech.glide:glide:${Versions.glide}"
    private const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    //picasso
    private const val picasso = "com.squareup.picasso:picasso::${Versions.picasso}"


    private const val leakCanary =
        "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    //navigation
    private const val navigation_ui =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    private const val navigation_fragment =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    private const val navigation_testing =
        "androidx.navigation:navigation-testing:${Versions.navigation}"

    // ViewModel
    private const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    //LiveData
    private const val lifecycleLiveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    private const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    private const val lifecycleViewModelSavedState =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
    private const val lifecycleCompiler =
        "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    private const val lifecycleTestVersion =
        "androidx.arch.core:core-testing:${Versions.lifecycleTestVersion}"

    //room
    private const val room = "androidx.room:room-runtime:${Versions.room}"
    private const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    private const val roomTest = "androidx.room:room-testing:${Versions.room}"
    private const val preference = "androidx.preference:preference-ktx:${Versions.preference}"

    private const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    private const val rxkotlin = "io.reactivex.rxjava3:rxkotlin:${Versions.coroutines}"

    private const val taptargetview =
        "com.getkeepsafe.taptargetview:taptargetview:${Versions.taptargetview}"

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

    private const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    private const val debugFragment = "androidx.fragment:fragment-testing:${Versions.fragment}"

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
        add(glideBase)
//        add(navigation_ui)
        add(lifecycleViewModelSavedState)
        add(lifecycle)
        add(lifecycleLiveData)
        add(lifecycleViewModel)
        add(fragment)
//        add(room)
//        add(preference)
//        add(coroutines)
//        add(taptargetview)
    }

    val kapt = arrayListOf<String>().apply {
        add(glideCompiler)
        add(lifecycleCompiler)
//        add(roomCompiler)
        add(daggerCompiler)
        add(daggerSupportCompiler)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
//        add(navigation_testing)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
        add(lifecycleTestVersion)
//        add(roomTest)
    }

    val debugImplementation = arrayListOf<String>().apply {
        add(leakCanary)
        add(debugFragment)
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