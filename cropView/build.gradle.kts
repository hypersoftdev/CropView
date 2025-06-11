plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.hypersoft.crop"
    compileSdk = 35

    defaultConfig {
        minSdk = 23
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)

    // Exif (for rotation)
    implementation(libs.androidx.exifinterface)
}


publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.hypersoft.cropview"
            artifactId = "cropview"
            version = "1.0.3"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
