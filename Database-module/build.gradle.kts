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
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.0") // Для тестирования на Kotlin
    implementation(libs.io.ktor.serialization.kotlinx.json)
    implementation(libs.org.jetbrains.exposed.core)
    implementation(libs.org.jetbrains.exposed.dao)
    implementation(libs.org.jetbrains.exposed.jdbc)
    implementation(libs.org.postgresql)
    implementation("com.h2database:h2:1.4.200")
    implementation(project(":common-module"))

}

kotlin {
    jvmToolchain(21)
}