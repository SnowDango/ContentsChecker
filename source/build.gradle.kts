val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

val appMainClass: String = "cc.snowdango.ApplicationKt"

plugins {
    application
    kotlin("jvm") version "1.5.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.10"
}

group = "cc.snowdango"
version = "0.0.1"
application {
    mainClass.set(appMainClass)
}

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.3")
    implementation("io.ktor:ktor-html-builder:$ktor_version")
    implementation("org.jetbrains:kotlin-css-jvm:1.0.0-pre.129-kotlin-1.4.20")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = appMainClass
    }
    from(
        configurations.compile.get().map {
            if (it.isDirectory) it else zipTree(it)
        }
    )
}
