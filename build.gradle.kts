plugins {
    kotlin("jvm") version "1.8.0"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "com.zaleslaw.kotlindl.serverside.release-testing-app"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.jetbrains.kotlinx:kotlin-deeplearning-tensorflow:0.5.2")
    implementation ("org.jetbrains.kotlinx:kotlin-deeplearning-dataset:0.5.2")
    implementation ("org.jetbrains.kotlinx:kotlin-deeplearning-onnx:0.5.2")
    implementation ("org.jetbrains.kotlinx:kotlin-deeplearning-visualization:0.5.2")
    implementation ("org.apache.logging.log4j:log4j-api:2.17.2")
    implementation ("org.apache.logging.log4j:log4j-core:2.17.2")
    implementation ("org.apache.logging.log4j:log4j-slf4j-impl:2.17.2")
    testImplementation(kotlin("test"))

    // to run on GPU (if CUDA is updated and machine with NVIDIA onboard)
    /*implementation 'org.tensorflow:libtensorflow:1.15.0'
    implementation 'org.tensorflow:libtensorflow_jni_gpu:1.15.0'
    api 'com.microsoft.onnxruntime:onnxruntime_gpu:1.12.1'
    */
}

tasks.test {
    useJUnitPlatform()
    // set heap size for the test JVM(s)
    minHeapSize = "1024m"
    maxHeapSize = "8g"
}

tasks{
    shadowJar {
        manifest {
            attributes(Pair("Main-Class", "MainKt"))
            attributes(Pair("Implementation-Version", "1.15"))
        }
    }
}

kotlin {
    jvmToolchain(11)
}

