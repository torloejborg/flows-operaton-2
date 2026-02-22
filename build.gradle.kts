
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
//    implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter:7.20.0")
//    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.camunda.bpm:camunda-engine:7.24.0")
    // Source: https://mvnrepository.com/artifact/org.camunda.bpm.springboot/camunda-bpm-spring-boot-starter
    implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter:7.24.0")
    // Exclude duplicate jaxb dependencies
//    configurations.all {
//        exclude(group = "org.glassfish.jaxb", module = "jaxb-core")
//        exclude(group = "com.sun.xml.bind", module = "jaxb-core")
//    }
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


