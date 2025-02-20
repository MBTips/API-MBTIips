
extra["springCloudVersion"] = "2024.0.0"

dependencies {
    implementation(project(mapOf("path" to ":core")))

    // Spring Context
    implementation("org.springframework:spring-context:6.2.2")
    // Open Feign
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}