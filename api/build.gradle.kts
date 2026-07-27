plugins {
    id("mcpets.java.standard")
}

dependencies {
    compileOnly(libs.jetbrains.annotations)
}

tasks.javadoc {
    enabled = project.hasProperty("enable-javadoc")
}