import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform") version "1.7.10"
    id("com.android.library")
    id("com.prof18.kmp-framework-bundler") version "0.0.1"
}

val libraryVersion = "1.0"

group = "com.prof18"
version = libraryVersion

repositories {
//    mavenLocal()
    google()
    mavenCentral()
}

kotlin {

    val xcFramework = XCFramework("LibraryName")

    android()

    // Enable for FAT_FRAMEWORK and XC_FRAMEWORK_LEGACY_BUILD
//    ios {
//        binaries.framework("LibraryName")
//    }

    // Enable for XC_FRAMEWORK_LEGACY
    ios {
        binaries.framework("LibraryName") {
            xcFramework.add(this)
        }
    }

//    macosX64("macOS") {
//        binaries.framework("LibraryName")
//    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.google.android.material:material:1.6.1")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting
        val iosTest by getting
    }
}

android {
    compileSdk = 30
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 30
    }
}

frameworkBundlerConfig {
    frameworkName.set("LibraryName")

    // Enable for FAT_FRAMEWORK
//    outputPath.set("$rootDir/../test-dest")

    // Enable for XC_FRAMEWORK
    outputPath.set("$rootDir/../test-dest-xc")

    versionName.set(libraryVersion)
    frameworkType = com.prof18.kmpframeworkbundler.data.FrameworkType.XC_FRAMEWORK
//    frameworkType = com.prof18.kmpframeworkbundler.data.FrameworkType.XC_FRAMEWORK_LEGACY_BUILD
//    frameworkType = com.prof18.kmpframeworkbundler.data.FrameworkType.FAT_FRAMEWORK

    cocoaPodRepoInfo {
        summary.set("This is a test KMP framework")
        homepage.set("https://github.com/prof18/cocoa-repo-xcframework-test")
        license.set("Apache")
        authors.set("\"Marco Gomiero\" => \"mg@me.com\"")
        gitUrl.set("git@github.com:prof18/cocoa-repo-xcframework-test.git")
    }
}
