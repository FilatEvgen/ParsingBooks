plugins {
    kotlin("jvm")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":Database-module"))
    implementation(project(":common-module"))

}

kotlin {
    jvmToolchain(21)
}