import org.springframework.boot.gradle.tasks.bundling.BootJar

val queryDslVersion: String by project

plugins {
    kotlin("kapt")
    kotlin("plugin.jpa")
    kotlin("plugin.allopen")
}

dependencies {
    implementation(project(":application"))

    // MySQL
    runtimeOnly("com.mysql:mysql-connector-j")

    // JDBC
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    // JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // QueryDSL
    implementation("io.github.openfeign.querydsl:querydsl-core:$queryDslVersion")
    implementation("io.github.openfeign.querydsl:querydsl-jpa:$queryDslVersion")
    kapt("io.github.openfeign.querydsl:querydsl-apt:$queryDslVersion:jpa")
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true
