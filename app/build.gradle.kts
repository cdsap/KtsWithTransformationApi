import com.malinskiy.marathon.execution.AnnotationFilter
import com.transformer.annotationchecker.AnnotationReducerPlugin

plugins.apply(AnnotationReducerPlugin::class.java)
plugins {
    id("com.android.application")
    id("kotlin-android")
    //  id("marathon")
    id("marathon")
    id("kotlin-android-extensions")
}


android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.example.ivillar.myapplication"
        minSdkVersion(15)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {

    }
}

dependencies {
    //   implementation fileTree (dir: 'libs', include: ['*.jar'])
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.2.61")
    implementation("com.android.support:appcompat-v7:28.0.0")
    implementation("com.android.support.constraint:constraint-layout:1.1.3")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("com.android.support.test:runner:1.0.2")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")
}

marathon {
    name = "test"
    ignoreFailures = true
    fallbackToScreenshots = false
    batchingStrategy {
        fixedSize {
            size = 10
        }
    }
    flakinessStrategy {
        probabilityBased {
            minSuccessRate = 0.8
            maxCount = 3
            //  timeLimit = Instant.now().minus(1, ChronoUnit.DAYS).truncatedTo(ChronoUnit.DAYS)
        }
    }

    sortingStrategy {
        executionTime {
            percentile = 90.0
            // timeLimit = Instant.now().minus(30, ChronoUnit.DAYS)
        }
    }

    filteringConfiguration {
        whitelist {

            // testClassRegexes = empt
            val a = ""
            // add(AnnotationFilter(a.toRegex()))

            if (!a.isEmpty()) {
                add(AnnotationFilter(a.toRegex()))
            }
            //   a/dd(AnnotationFilter("com.example.ivillar.myapplication.AnnotationB".toRegex()))
            //    add(AnnotationFilter("test" .toRegex()))

            //   annotationFilter = includedAnnotationArgument()
        }
        blacklist {
            //   add(AnnotationFilter("com.example.ivillar.myapplication.AnnotationB".toRegex()))
            //     annotationFilter = excludedAnnotationArgument()
        }
    }
}