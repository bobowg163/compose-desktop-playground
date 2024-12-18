import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

group = "bobowg"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality

    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.9.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        javaHome = System.getenv("JAVA_HOME")
        nativeDistributions {
            modules("java.sql")
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Compose-for-desktopplayground"
            packageVersion = "1.0.0"

            linux {
                packageVersion = "1.0.1"
                debPackageVersion = "1.0.1"
                rpmPackageVersion = "1.0.1"
                iconFile.set(project.file("icon.png"))
            }
            macOS {
                packageVersion = "1.0.0"
                dmgPackageVersion = "1.0.0"
                pkgPackageVersion = "1.0.0"
            }
            windows {
                packageVersion = "1.0.0"
                exePackageVersion = "1.0.0"
                msiPackageVersion = "1.0.0"
            }
        }
    }
}
