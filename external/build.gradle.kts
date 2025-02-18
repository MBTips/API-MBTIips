
extra["springCloudVersion"] = "2024.0.0"

dependencies {
    implementation(project(mapOf("path" to ":core")))

    implementation("org.springframework.boot:spring-boot-starter-web")
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