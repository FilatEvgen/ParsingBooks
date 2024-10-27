plugins {
    kotlin("jvm")
    alias(libs.plugins.kotlinx.serialization)
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.io.ktor.serialization.kotlinx.json)
    implementation(libs.io.ktor.server.core)
    implementation(libs.io.ktor.server.netty)
    implementation(libs.io.ktor.server.content.negotiation)
    implementation(project(":common-module"))
    implementation(project(":parsing-book"))
}

kotlin {
    jvmToolchain(21)
}