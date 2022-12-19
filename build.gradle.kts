import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val jacksonVersion = "2.14.1"
val kotlinxBenchmarkVersion = "0.4.6"
val kotlinxSerializationVersion = "1.4.1"
val gsonVersion = "2.10"

plugins {
    kotlin("jvm") version "1.7.21"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.6"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.7.21"
    kotlin("plugin.serialization") version "1.8.0-RC"
    application
}

group = "com.joaco"
version = "1.0-SNAPSHOT"
repositories {
    mavenCentral()
}

dependencies {

    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:$kotlinxBenchmarkVersion")


    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${kotlinxSerializationVersion}")
    implementation("com.google.code.gson:gson:${gsonVersion}")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

application {
    mainClass.set("MainKt")
}

benchmark {
    configurations {
//        named("main") {
//            warmups = 5
//            iterations = 5
//            iterationTime = 3
//            mode="avgt"
//        }
    }
    targets {
        register("main") {
            this as kotlinx.benchmark.gradle.JvmBenchmarkTarget
            jmhVersion = "1.36"
        }
    }
}

allOpen {
    annotation("org.openjdk.jmh.annotations.State")
}

sourceSets.all {
    java.setSrcDirs(listOf("$name/src"))
    resources.setSrcDirs(listOf("$name/resources"))
}

