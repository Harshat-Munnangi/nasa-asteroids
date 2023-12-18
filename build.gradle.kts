plugins {
    id("java")
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "org.nasa.neo"
version = "1"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-parent:3.2.0")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework:spring-core:6.1.2")
    implementation("org.springframework:spring-context:6.1.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks {
    test {
        useJUnitPlatform()
    }
}
