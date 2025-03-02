
extra["springCloudVersion"] = "2024.0.0"

dependencies {
    implementation(project(mapOf("path" to ":core")))

    // Jackson
    implementation("com.fasterxml.jackson.core:jackson-databind")

    // Open Feign
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.bootJar {
    enabled = false
}