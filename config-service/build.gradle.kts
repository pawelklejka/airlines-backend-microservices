plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("com.bmuschko.docker-spring-boot-application")
}

// spring boot related tools version should match spring boot version
version = property("springBootVersion") ?: error("Cannot find springBootVersion property in gradle.properties")

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.cloud:spring-cloud-config-server")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")
}

tasks.bootJar {
    launchScript()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}