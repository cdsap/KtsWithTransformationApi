package com.transformer.annotationchecker


import android.databinding.tool.ext.toClassName
import android.databinding.tool.ext.toJavaCode
import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation
import com.android.utils.FileUtils
import net.bytebuddy.jar.asm.ClassReader
import net.bytebuddy.jar.asm.Type
import java.io.File
import java.io.FileInputStream
import java.net.URLClassLoader

class AnnotationsChecker : Transform() {
    override fun getName(): String {
        return "TransformerFux"
    }

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> {
        return mutableSetOf(QualifiedContent.DefaultContentType.CLASSES)
    }

    override fun isIncremental(): Boolean = false


    override fun getScopes(): MutableSet<in QualifiedContent.Scope> {
        return mutableSetOf(QualifiedContent.Scope.PROJECT)
    }

    override fun transform(transformInvocation: TransformInvocation?) {
        transformInvocation?.inputs?.forEach {
            it.directoryInputs.forEach {

                val dest = transformInvocation.outputProvider.getContentLocation(
                    it.name,
                    it.contentTypes,
                    it.scopes,
                    Format.DIRECTORY
                )
                FileUtils.copyDirectory(it.file, dest)


            }

            it.jarInputs.forEach {
                val dest = transformInvocation.outputProvider.getContentLocation(
                    it.name,
                    it.contentTypes,
                    it.scopes,
                    Format.JAR
                )
                FileUtils.copyDirectory(it.file, dest)

            }
        }

    }
}