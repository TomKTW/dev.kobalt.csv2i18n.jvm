plugins {
    kotlin("jvm") version "1.6.0"
    id("com.github.johnrengelman.shadow") version "6.0.0"
}

group = "dev.kobalt"
version = "0000.00.00.00.00.00.000"

repositories {
    mavenCentral()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

fun kobalt(module: String, version: String) = "dev.kobalt:$module:$version"
fun ktor(module: String, version: String) = "io.ktor:ktor-$module:$version"
fun exposed(module: String, version: String) = "org.jetbrains.exposed:exposed-$module:$version"
fun general(module: String, version: String) = "$module:$version"
fun kotlinx(module: String, version: String) = "org.jetbrains.kotlinx:kotlinx-$module:$version"
fun kotlinw(module: String, version: String) = "org.jetbrains.kotlin-wrappers:kotlin-$module:$version"

fun DependencyHandler.csvParser() {
    implementation(general("com.github.doyaaaaaken:kotlin-csv-jvm", "0.7.3"))
}

fun DependencyHandler.standardLibrary() {
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.3")
    implementation(kotlin("stdlib", "1.5.21"))
}

fun DependencyHandler.logger() {
    implementation(general("ch.qos.logback:logback-classic", "1.2.3"))
}

dependencies {
    standardLibrary()
    csvParser()
    logger()
}

tasks {
    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveFileName.set("csv2i18n.jar")
        mergeServiceFiles()
        manifest {
            attributes("Main-Class" to "dev.kobalt.csv2i18n.jvm.MainKt")
        }
    }
}