plugins {
    id("mcpets.java.platform")
    id("mcpets.runs.paper")
}

dependencies {
    implementation(projects.common)

    compileOnly(libs.paper)
    implementation(libs.cloud.paper)
    implementation(libs.bstats.base)

    compileOnly(libs.placeholderapi)
    compileOnly(libs.mythic)
    compileOnly(libs.modelengine)
    compileOnly(libs.worldguard)
}

paperRuns {
    default("26.2", java = 25)
}