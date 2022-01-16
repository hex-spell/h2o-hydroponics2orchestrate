import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.2"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
}

group = "com.modulariz"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-websocket")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.google.code.gson:gson:2.8.9")
	implementation("org.webjars:webjars-locator-core:0.48")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.6.2")
	implementation("mysql:mysql-connector-java:8.0.25")
	implementation("org.springframework.boot:spring-boot-starter-data-redis:2.6.2")
	implementation("org.slf4j:slf4j-api:1.7.32")
	implementation("ch.qos.logback:logback-classic:1.2.10")
	implementation("ch.qos.logback:logback-core:1.2.10")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
