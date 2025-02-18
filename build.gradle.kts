plugins {
    kotlin("jvm") version "2.1.10"
    application
}

group = "bk.edu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(22)
}
application {
    mainClass.set("bk.edu.MainKt")
}
