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
    testImplementation(kotlin("test"))
    implementation(libs.io.ktor.client.core)
    implementation(libs.io.ktor.client.cio)
    implementation(libs.io.ktor.client.content.negotiation)
    implementation(libs.io.ktor.serialization.kotlinx.json)
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}