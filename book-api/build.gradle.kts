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
    implementation(libs.org.jetbrains.exposed.core)
    implementation(libs.org.jetbrains.exposed.dao)
    implementation(libs.org.jetbrains.exposed.jdbc)
    implementation(libs.org.postgresql)
    implementation(libs.io.ktor.client.core)
    implementation(project(":common-module"))
    implementation(project(":parsing-book"))
    implementation(project(":Database-module"))
    implementation(project(":html-module"))
    implementation(project(":http-module"))

}

kotlin {
    jvmToolchain(21)
}