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
                println("directory $it")


                val fileName = it.file.absolutePath
                println("fileName $fileName")
                val dir = File(fileName)
                println("dir $dir")

                dir.walkTopDown().filter {
                    it.name.contains("ssTest")
                }
                println("2222")

                val dest = transformInvocation.outputProvider.getContentLocation(
                    it.name,
                    it.contentTypes,
                    it.scopes,
                    Format.DIRECTORY
                )
                FileUtils.copyDirectory(dir, dest)

                //  dest.deleteRecursively()
                //  dest.mkdirs()
//                println("dldlldldlddll")
//                val pathBitLen = it.file.toString().length
//                it.file.walkTopDown().forEach {
//
//                    val path = it.toString().substring(pathBitLen)
//                    if (it.isDirectory()) {
//                        // File(dest, path).mkdirs()
//                    } else {
//                        println(path)
//                        println(dest.absolutePath)
//                        FileUtils.copyFile(it, dest)
//                        // File(dest,path)
//
//                    }
//                    it.file.traverse {
//                        def path = "${it.toString().substring(pathBitLen)}"
//                        if (it.isDirectory()) {
//                            new File (outDir, path).mkdirs()
//                        } else {
//                            if (!path.endsWith("BuildConfig.class")) {
//                                new File (outDir, path).bytes = it.bytes
//                            }
//                        }
//                    }
//                }
//            }
//            it.file.walkTopDown().forEach {
//                println(it.name)
//                FileUtils.copyDirectory(it, dest)
//            }
                //    .map {
                //        println("xxxxxXxxx")
                //        FileUtils.copyDirectory(it, dest)
                //   }
//
                //              }
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

//        transformInvocation?.inputs?.forEach {
//
//            println("$it")
//            it.directoryInputs.forEach {
//                println("$it")
//                it.file.walkTopDown().forEach {
//                    // if (it.name.contains("2Test")) {
//                    if (it.isFile && it.name.contains("Test")) {
//                        println("Test -- > " + it.toString())
//                        val string = it.toString().split("debugAndroidTest/")
//                        val file =
//                            File("/Users/ivillar/testMarathon/MyApplication/app/build/tmp/kotlin-classes/debugAndroidTest/")
//                        val url = file.toURI().toURL()
//                        var urls = arrayOf(url)
//                        val classLoader = URLClassLoader(urls)
//                        val finalName = string[1].replace("/", ".").split(".class")
//                        val classAfter = classLoader.loadClass(finalName[0])
//                        classAfter.declaredMethods.asList().forEach {
//                            println("Method -- > " + it)
//                        }
//                        classAfter.annotations.asList().forEach {
//                            println("annotation -- > " + it)
//                        }
//
//                    }
//                    //   }
//                }
//              //  super.transform(transformInvocation)
//            }
//        }
        //   super.transform(transformInvocation)
    }

    private fun scanDirectory(ca: List<File>) {
        ca.forEach {
            val isDirectory: Boolean = it.isDirectory
            if (isDirectory) {
                scanDirectory(it.listFiles().asList())
            } else {
                //  println(it.path)
                if (it.name.contains("Test")) {
                    println("Test -- > " + it.toString())
                    it.javaClass.annotations.forEach {
                        if (mode.equals("prTests") && it.annotationClass.simpleName.equals("ChinaTest"))

                            println(it.toString())
                    }
                }
            }
        }
    }
}