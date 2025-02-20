dependencies {
    implementation(project(mapOf("path" to ":core")))

    // MySQL
    runtimeOnly("com.mysql:mysql-connector-j")

    // JPA
    api("org.springframework.boot:spring-boot-starter-data-jpa")

    // QueryDsl
    implementation("com.querydsl:querydsl-jpa:5.1.0:jakarta")
    annotationProcessor("com.querydsl:querydsl-apt:5.1.0:jakarta")
    annotationProcessor("jakarta.annotation:jakarta.annotation-api")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")
}

/**
 * QueryDSL Build Options
 */
var querydslDir = "${layout.projectDirectory}/build/generated/querydsl"

sourceSets {
    getByName("main").java.srcDirs(querydslDir)
}

tasks.withType<JavaCompile>().configureEach {
    options.generatedSourceOutputDirectory = file(querydslDir)
}


tasks.named("clean") {
    doLast {
        file(querydslDir).deleteRecursively()
    }
}
tasks.bootJar {
    enabled = false
}
