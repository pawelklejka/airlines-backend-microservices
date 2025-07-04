/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("ognl:ognl")
    implementation("com.itextpdf:itextpdf")
    implementation("com.itextpdf:html2pdf")
    implementation("com.itextpdf:kernel")
    implementation("com.itextpdf.tool:xmlworker")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.amqp:spring-rabbit-test")

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

docker {
    val registryUrl = "default-route-openshift-image-registry.apps-crc.testing"
    val registryProject = "airlines-backend-microservices"
    val moduleName = project.name

    springBootApplication {
        baseImage.set("amazoncorretto:21")
        jvmArgs.set(listOf("-Duser.name=developer"))
        mainClassName.set("com.bmuschko.app.Main")

        var images = setOf(
            "${registryUrl}/$registryProject/airlines-$moduleName:${version}",
        )
        if (version == "develop-SNAPSHOT") {
            images = images.plus("${registryUrl}/$registryProject/airlines-$moduleName:latest")
        }
        this.images.set(
            images,
        )
    }
}