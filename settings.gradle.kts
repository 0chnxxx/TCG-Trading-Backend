rootProject.name = "tcg-trading"

include("api")
include("application")
include("domain")
include("storage")

pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val springBootDependencyManagementVersion: String by settings

    plugins {
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version springBootDependencyManagementVersion
        kotlin("kapt") version kotlinVersion
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        kotlin("plugin.jpa") version kotlinVersion
        kotlin("plugin.allopen") version kotlinVersion
    }
}
