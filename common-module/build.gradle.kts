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
}

kotlin {
    jvmToolchain(21)
}