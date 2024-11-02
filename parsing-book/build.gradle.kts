plugins {
    kotlin("jvm")
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinx.serialization)
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.0") // Для тестирования на Kotlin
    implementation(project(":common-module"))
    implementation(libs.io.ktor.client.core)
    implementation(libs.io.ktor.client.cio)
    implementation(libs.io.ktor.client.content.negotiation)
    implementation(libs.io.ktor.serialization.kotlinx.json)

}

kotlin {
    jvmToolchain(21)
}