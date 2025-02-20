
dependencies {
    // Spring Context
    implementation("org.springframework:spring-context:6.2.2")

    // Jasypt
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")
}
tasks.bootJar {
    enabled = false
}