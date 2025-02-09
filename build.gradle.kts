import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val projectGroup: String by project
val applicationVersion: String by project
val javaVersion: String by project
val jvmVersion: String by project

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
}

// 공통 설정
allprojects {
    // 프로젝트 정보
    group = projectGroup
    version = applicationVersion

    // 라이브러리 저장소 지정
    repositories {
        mavenCentral()
    }

    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    // Java 코드 JVM 버전 지정
    java {
        sourceCompatibility = JavaVersion.valueOf(javaVersion)
        targetCompatibility = JavaVersion.valueOf(javaVersion)
    }

    // Kotlin 코드 JVM 버전 지정
    tasks.withType<KotlinCompile> {
        kotlin {
            compilerOptions {
                jvmTarget.set(JvmTarget.valueOf(jvmVersion))
                freeCompilerArgs.add("-Xjsr305=strict")
            }
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    // annotationProcessor 설정
    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }
}

// 공통 의존성
subprojects {
    dependencies {
        //Lombok
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")

        //Jackson
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        //Kotlin-Reflection
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        //Spring Boot Test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}
