import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

internal val Project.libs get() = the<LibrariesForLibs>()

android {
    compileSdk = AndroidConfig.compileSdkVersion

    defaultConfig {
        minSdk = AndroidConfig.minSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        resourceConfigurations += listOf("en", "ru")

        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName
    }

    signingConfigs {
        create("release") {}
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = AndroidConfig.isMinifyEnabled
            isShrinkResources = AndroidConfig.isShrinkResources
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            versionNameSuffix = "-r"
            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildConfig = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(libs.bundles.common)
    implementation(libs.bundles.ui.common)

    ksp(libs.dagger.hilt.compiler)

    implementation(project(":core:common"))
    implementation(project(":ui:core:common"))
    implementation(project(":ui:core:navigation"))
}