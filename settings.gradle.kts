plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "ParsingBook"
include(":book-api")
include(":parsing-book")
include("Database-module")
include("common-module")
