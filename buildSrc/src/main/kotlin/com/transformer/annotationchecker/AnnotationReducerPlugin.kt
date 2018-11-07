package com.transformer.annotationchecker

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.language.nativeplatform.internal.BuildType

class AnnotationReducerPlugin : Plugin<Project> {
    override fun apply(project: Project) = project.run {
        val android = project.extensions.getByType(AppExtension::class.java)
        android.registerTransform(AnnotationsChecker())
    }
}