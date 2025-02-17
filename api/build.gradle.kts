import org.springframework.boot.gradle.tasks.bundling.BootJar

val swaggerVersion: String by project

dependencies {
    implementation(project(":application"))
    implementation(project(":domain"))
    runtimeOnly(project(":storage"))

    // Spring Web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Spring Security
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-data")
    implementation("org.springframework.security:spring-security-test")

    // Swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$swaggerVersion")
}

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = true
jar.enabled = true
