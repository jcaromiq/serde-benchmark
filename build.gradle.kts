import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val jacksonVersion = "2.13.3"
val kotlinxBenchmarkVersion = "0.4.4"

plugins {
    kotlin("jvm") version "1.7.10"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.4"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"
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
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0-RC")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
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
            jmhVersion = "1.35"
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

