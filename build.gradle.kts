plugins {
    java
    id("org.springframework.boot") version "3.5.6"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "org.tierlistapp"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // REST/веб-слой (Spring MVC + встроенный Tomcat + Jackson)
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Swagger UI
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.13")

    // Spring Data MongoDB (MongoTemplate, MongoRepository, драйвер)
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

    // S3 storage
    implementation(platform("software.amazon.awssdk:bom:2.44.4"))
    implementation("software.amazon.awssdk:s3")
    implementation("software.amazon.awssdk:auth")
    implementation("software.amazon.awssdk:regions")
    implementation("software.amazon.awssdk:url-connection-client")

    // Валидация (@Valid, @NotBlank и т.д.)
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Healthcheck и метрики
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // Mapstruct
    implementation("org.mapstruct:mapstruct:1.5.5.Final")

    // Lombok
    compileOnly("org.projectlombok:lombok")

    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

    // Тесты
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}