plugins {
	java
	id("org.springframework.boot") version "3.5.7"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.vyshali"
version = "0.0.1-SNAPSHOT"
description = "Eureka Server - Service Discovery"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

ext {
	set("springCloudVersion", "2024.0.0")
}

dependencies {
	// Eureka Server
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")

	// Spring Boot Actuator
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	// Development Tools
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.bootJar {
	archiveFileName.set("eureka-server.jar")
}
