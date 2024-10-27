plugins {
    kotlin("jvm") version "2.0.0"
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinx.serialization)
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
    implementation(libs.io.ktor.serialization.kotlinx.json)
    implementation(libs.org.jetbrains.exposed.core)
    implementation(libs.org.jetbrains.exposed.dao)
    implementation(libs.org.jetbrains.exposed.jdbc)
    implementation(libs.org.postgresql)
    implementation(libs.io.ktor.server.core)
    implementation(libs.io.ktor.server.netty)
    implementation(libs.io.ktor.server.content.negotiation)
    implementation(project(":parsing-book"))
    implementation(project(":book-api"))
    implementation(project(":Database-module"))
    implementation(project(":common-module"))
}

kotlin {
    jvmToolchain(21)
}
