import org.gradle.kotlin.dsl.`kotlin-dsl`

val kotlinVersion = "1.2.61"
val playPublisher = System.getenv("PUBLISHER_VERSION") ?: "2.0.0-beta2"

buildscript {
    val kotlinVersion = "1.2.61"

    repositories {
        jcenter()
        mavenCentral()
        google()
        maven { url = uri("https://maven.fabric.io/public") }
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}


plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

repositories {
    mavenLocal()
    google()
    jcenter()
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jre8:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
}

gradlePlugin {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
    }

    dependencies {
        implementation("com.android.tools.build:gradle:3.2.1")
        implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        implementation("com.malinskiy.marathon:marathon-gradle-plugin:0.2.2-SNAPSHOT")
        implementation("net.bytebuddy:byte-buddy:1.9.2")
    }


}
