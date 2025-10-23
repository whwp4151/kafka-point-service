import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    // kafka
    implementation("org.springframework.kafka:spring-kafka")

    // swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.13")
}
