
plugins {
    id("org.springframework.boot") version "4.0.3"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.vaadin") version "25.0.5"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

extra["vaadinVersion"] = "25.0.5"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.vaadin:vaadin-spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    developmentOnly("com.vaadin:vaadin-dev")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.camunda.bpm:camunda-engine:7.24.0")
    implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter:7.24.0")
    implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter-webapp:7.24.0")

}

// Force Vaadin version to avoid conflicts
dependencyManagement {
    imports {
        mavenBom("com.vaadin:vaadin-bom:${property("vaadinVersion")}")
    }
}

tasks.withType<JavaCompile> {
    sourceCompatibility = "21"
    targetCompatibility = "21"
}


tasks.withType<Test> {
    useJUnitPlatform()
}


