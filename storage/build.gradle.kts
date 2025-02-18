import org.springframework.boot.gradle.tasks.bundling.BootJar

val queryDslVersion: String by project

dependencies {
    implementation(project(":application"))
    implementation(project(":domain"))

    // MySQL
    runtimeOnly("com.mysql:mysql-connector-j")

    // JDBC
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    // JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // QueryDSL
    implementation("io.github.openfeign.querydsl:querydsl-core:$queryDslVersion")
    implementation("io.github.openfeign.querydsl:querydsl-jpa:$queryDslVersion")
}

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true
