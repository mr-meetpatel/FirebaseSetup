import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    google()  // Google's Maven repository
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"));

    implementation("com.google.firebase:firebase-admin:6.12.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.1.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.5")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.3.1") // for kotest framework
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.3.1") // for kotest core jvm assertions
    testImplementation("io.kotest:kotest-property-jvm:5.3.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1") // for kotest property test
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}