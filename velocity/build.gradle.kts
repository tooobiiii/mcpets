plugins {
    id("mcpets.java.platform")
    id("mcpets.runs.velocity")
}

dependencies {
    implementation(projects.common)

    compileOnly(libs.velocity)
    annotationProcessor(libs.velocity)
    compileOnly(libs.bstats.velocity)
    compileOnly(libs.cloud.velocity)
}