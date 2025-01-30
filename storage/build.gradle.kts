import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    implementation(project(":application"))

    //JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    //JDBC
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    //MySQL
    runtimeOnly("com.mysql:mysql-connector-j")
}
