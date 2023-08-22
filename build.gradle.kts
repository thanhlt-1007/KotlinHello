// For `KotlinCompile` task below
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "3.1.2"
  id("io.spring.dependency-management") version "1.1.2"
  // The version of Kotlin to use
  kotlin("jvm") version "1.7.21"
  // The Kotlin Spring plugin
  kotlin("plugin.spring") version "1.7.21"
}

group = "learn.kotlinsb"
version = "0.0.1-SNAPSHOT"

java {
  sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
  mavenCentral()
}

dependencies {
  //
  // IMPLEMENTATION
  //
  implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
  implementation("org.springframework.boot:spring-boot-starter-web")
  // Jackson extensions for Kotlin for working with JSON
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  // Kotlin reflection library, required for working with Spring
  implementation("org.jetbrains.kotlin:kotlin-reflect")

  //
  // RUNTIME ONLY
  //
  runtimeOnly("com.h2database:h2")

  //
  // TEST IMPLEMENTATION
  //
  testImplementation("org.springframework.boot:spring-boot-starter-test")
}

// Settings for `KotlinCompile` tasks
tasks.withType<KotlinCompile> {
  // Kotlin compiler options
  kotlinOptions {
    // `-Xjsr305=strict` enables the strict mode for JSR-305 annotations
    freeCompilerArgs += "-Xjsr305=strict"
    // This option specifies the target version of the generated JVM bytecode
    jvmTarget = "17"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
