plugins {
    id("io.spring.dependency-management")
    id("org.springframework.boot")
    id("com.bmuschko.docker-spring-boot-application")
}


// spring boot related tools version should match spring boot version
version = property("springBootVersion") ?: error("Cannot find springBootVersion property in gradle.properties")

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
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