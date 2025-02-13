import org.springframework.boot.gradle.tasks.bundling.BootJar

val jwtVersion: String by project

dependencies {
    implementation(project(":domain"))

    // Spring Context
    implementation("org.springframework:spring-context")

    // Spring Transaction
    implementation("org.springframework:spring-tx")

    // JWT
    implementation("io.jsonwebtoken:jjwt-api:$jwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:$jwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jwtVersion")
}

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true
