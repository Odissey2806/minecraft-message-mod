plugins {
    id("fabric-loom") version "1.11.7"
}

version = "1.0.0"
group = "com.github.username"

base {
    archivesName.set("message-mod")
}

repositories {
    mavenCentral()
    maven {
        name = "Fabric"
        url = uri("https://maven.fabricmc.net/")
    }
    maven {
        name = "Forge"
        url = uri("https://maven.minecraftforge.net/")
    }
}

dependencies {
    // Minecraft и Fabric
    minecraft("com.mojang:minecraft:1.21.1")
    mappings("net.fabricmc:yarn:1.21.1+build.1:v2")

    modImplementation("net.fabricmc:fabric-loader:0.17.0")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.102.1+1.21.1")

    // Hibernate и PostgreSQL
    implementation("org.hibernate:hibernate-core:6.5.2.Final")
    implementation("org.hibernate:hibernate-hikaricp:6.5.2.Final")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation("jakarta.transaction:jakarta.transaction-api:2.0.1")
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("org.postgresql:postgresql:42.7.3")

    // Включение в финальный JAR
    include("org.hibernate:hibernate-core:6.5.2.Final")
    include("org.hibernate:hibernate-hikaricp:6.5.2.Final")
    include("jakarta.persistence:jakarta.persistence-api:3.1.0")
    include("jakarta.transaction:jakarta.transaction-api:2.0.1")
    include("com.zaxxer:HikariCP:5.1.0")
    include("org.postgresql:postgresql:42.7.3")
}

tasks.processResources {
    inputs.property("version", project.version)
    filesMatching("fabric.mod.json") {
        expand("version" to project.version)
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(21)
}

java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.jar {
    from("LICENSE") {
        rename { "${it}_${project.base.archivesName.get()}" }
    }
}
