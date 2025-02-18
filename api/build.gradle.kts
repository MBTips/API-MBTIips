
extra["springCloudVersion"] = "2024.0.0"

dependencies {
    // Contain Project
    implementation(project(mapOf("path" to ":core")))
    implementation(project(mapOf("path" to ":external")))
    implementation(project(mapOf("path" to ":repository")))

    // Spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // Swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.3")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}



