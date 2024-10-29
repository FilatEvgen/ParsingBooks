plugins {
    kotlin("jvm")
    alias(libs.plugins.ktor)
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.io.ktor.client.core)
    implementation(libs.io.ktor.client.cio)
    implementation(libs.io.ktor.client.content.negotiation)
}

kotlin {
    jvmToolchain(21)
}