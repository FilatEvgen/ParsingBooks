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
    testImplementation(kotlin("test"))
    implementation(libs.io.ktor.serialization.kotlinx.json)
    implementation(libs.org.jetbrains.exposed.core)
    implementation(libs.org.jetbrains.exposed.crypt)
    implementation(libs.org.jetbrains.exposed.dao)
    implementation(libs.org.jetbrains.exposed.jdbc)
    implementation(libs.org.jetbrains.exposed.kotlin.datetime)
    implementation(libs.org.postgresql)
    implementation(libs.io.ktor.server.core)
    implementation(libs.io.ktor.server.netty)
    implementation(libs.io.ktor.server.content.negotiation)
    implementation(libs.io.ktor.server.html.builder)
    implementation(project(":common-module"))
    implementation(project(":parsing-book"))

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}