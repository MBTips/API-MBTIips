plugins {
    java
    id("org.springframework.boot") version "3.4.2"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

// 루트 프로젝트, 서브 프로젝트 모두 설정
allprojects {
    group = "com.mbtips"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

// 하위 프로젝트 설정
subprojects {
    apply {
        plugin("java")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("java-library")
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    dependencies {
        // lombok
        implementation("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")

        // TEST
        testImplementation(platform("org.junit:junit-bom:5.10.0"))
        testImplementation("org.junit.jupiter:junit-jupiter")

    }

    tasks.test {
        useJUnitPlatform()
    }
}