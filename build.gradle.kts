// build.gradle.kt
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.3.21"
    id("org.jetbrains.kotlin.jvm") version kotlinVersion
    application
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    api("io.javalin:javalin:2.8.0")
    implementation("org.slf4j:slf4j-simple:1.7.26")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")

    testImplementation("org.junit.jupiter:junit-jupiter:5.4.1")
    testImplementation("com.github.kittinunf.fuel:fuel:2.0.1")
    testImplementation("com.github.kittinunf.fuel:fuel-jackson:2.0.1")
}

repositories {
    jcenter()
}

application {
    // Define the main class for the application
    mainClassName = "ApplicationKt"
}