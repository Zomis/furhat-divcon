plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.24"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

apply(plugin = "java")
apply(plugin = "kotlin")

// Defines what version of Java to use.
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

// Defines how Kotlin should compile.
tasks.compileKotlin {
//    sourceCompatibility = "1.8"
//    targetCompatibility = "1.8"

    kotlinOptions {
        // Defines what JVM bytecode to use, 1.8 rather than 1.6
        jvmTarget = "1.8"
        apiVersion = "1.8"
        languageVersion = "1.8"
    }
}

// Defines how Kotlin should compile when testing.
// Try to keep it the same as compileKotlin.
tasks.compileTestKotlin {
//    sourceCompatibility = "1.8"
//    targetCompatibility = "1.8"

    kotlinOptions {
        // Defines what JVM bytecode to use, 1.8 rather than 1.6
        jvmTarget = "1.8"
        apiVersion = "1.8"
        languageVersion = "1.8"
    }
}

repositories {
    mavenLocal()
    mavenCentral()

    maven {
        url = uri("https://s3-eu-west-1.amazonaws.com/furhat-maven/releases")
    }

    maven {
        url = uri("https://repo.gradle.org/gradle/libs-releases")
    }
}

dependencies {
    implementation("com.furhatrobotics.furhatos:furhat-commons:2.9.1")
}

// These new blocks are needed to package your project into a working skill file.
tasks.jar {
    val lowerCasedName = archiveBaseName.get().lowercase()
    val normalizedName =
        lowerCasedName.substring(0, 1).uppercase() + lowerCasedName.substring(1)

    manifest {
        attributes(
            "Class-Path" to configurations.compileClasspath.get()
                .joinToString(" ") { it.name },
            "Main-Class" to "furhatos.app.$lowerCasedName.${normalizedName}Skill"
        )
    }
}

tasks.shadowJar {
    exclude("**/Log4j2Plugins.dat")
    exclude("**/node_modules")

    from("skill.properties")
    from("assets")

    archiveExtension.set("skill")
}
