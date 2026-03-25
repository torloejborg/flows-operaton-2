
plugins {
    id("org.springframework.boot") version "4.0.4"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.vaadin") version "25.0.5"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

extra["vaadinVersion"] = "25.0.5"

dependencies {

    compileOnly("org.projectlombok:lombok:1.18.42")
    annotationProcessor("org.projectlombok:lombok:1.18.42")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.vaadin:vaadin-spring-boot-starter")
    developmentOnly("com.vaadin:vaadin-dev")
    runtimeOnly("com.h2database:h2")

    implementation("org.operaton.bpm.springboot:operaton-bpm-spring-boot-starter:2.0.0")
    implementation("org.operaton.bpm.springboot:operaton-bpm-spring-boot-starter-webapp:2.0.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testCompileOnly("org.projectlombok:lombok:1.18.42")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.42")

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


