import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.6.10"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.6.10"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.6.10"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

group = "de.emaarco"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    // Starter & Database
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.6.2")
    runtimeOnly("org.postgresql:postgresql")

    // Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Camunda
    implementation("org.camunda.bpm:camunda-engine-spring:7.16.0")
    implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter:7.16.0")
    implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter-webapp:7.16.0")

    // Drools (Base)
    implementation("org.drools:drools-core:7.63.0.Final")
    implementation("org.kie:kie-dmn-core:7.63.0.Final")

    // Drools (Extensions to support PMML)
    implementation("org.drools:kie-pmml:7.63.0.Final")
    implementation("org.kie:kie-dmn-jpmml:7.63.0.Final")
    implementation("org.jpmml:pmml-evaluator:1.5.16")

    // Other dependencies
    implementation("org.json:json:20211205")

    // Unit-testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
