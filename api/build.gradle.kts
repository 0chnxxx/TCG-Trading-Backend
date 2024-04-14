import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = true
jar.enabled = true

dependencies {
    implementation(project(":application"))
    runtimeOnly(project(":storage"))

    //Spring Web
    implementation("org.springframework.boot:spring-boot-starter-web")

    //Spring Validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    //Swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
}
