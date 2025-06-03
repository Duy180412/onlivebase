    plugins {
        alias(libs.plugins.android.application)
        alias(libs.plugins.kotlin.android)
        alias(libs.plugins.kotlin.compose)
        alias(libs.plugins.dagger.hilt.android)
        alias(libs.plugins.ksp)

    }


    android {
        namespace = "vn.vplay.vlive.myapplication"
        compileSdk = 34
        flavorDimensions += "onlivetv"
        buildFeatures {
            buildConfig = true
        }

        defaultConfig {
            applicationId = "vn.vplay.vlive.myapplication"
            minSdk = 24
            targetSdk = 34
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }

        productFlavors {
            create("dev") {
                dimension = "onlivetv"
                buildConfigField("String", "BASE_URL_ONLIVE", "\"https://tv-api-dev-api.onlivetv.vn/api/v3/\"")
            }

            create("prod") {
                dimension = "onlivetv"
                buildConfigField("String", "BASE_URL_ONLIVE", "\"https://tv-api.onlivetv.vn/api/v3/\"")

            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
        kotlinOptions {
            jvmTarget = "11"
        }
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.13"
        }
    }

    dependencies {

        //hilt
        implementation(libs.hilt.android)
        ksp(libs.hilt.compiler)
        implementation(libs.androidx.hilt.navigation.compose)
        ksp(libs.androidx.hilt.compiler)

        // retrofit 
        implementation(libs.retrofit)
        implementation(libs.converter.gson)
        implementation(libs.okhttp)
        implementation(libs.logging.interceptor)

        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.foundation)
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        implementation(libs.androidx.activity.compose)
        implementation(libs.androidx.ui)
        implementation(libs.androidx.ui.graphics)
        implementation(libs.androidx.ui.tooling.preview)
        implementation(libs.androidx.material3)
        implementation (libs.androidx.navigation.compose )
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.ui.test.junit4)
        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)
    }