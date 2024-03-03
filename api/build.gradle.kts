import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = true
jar.enabled = true

dependencies {
    implementation(project(":application"))
    implementation(project(":domain"))
    runtimeOnly(project(":storage"))

    //Web
    implementation("org.springframework.boot:spring-boot-starter-web")
}
