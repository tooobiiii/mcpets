plugins {
    `java-library`
    id("mcpets.java.standard")
    alias(libs.plugins.blossom)

}

dependencies {
    api(projects.api)
    compileOnlyApi(libs.bundles.adventure)
    compileOnlyApi(libs.bundles.adventure.serializers)
    compileOnlyApi(libs.bundles.adventure.minimessage)
    compileOnlyApi(libs.snakeyaml)
    compileOnlyApi(libs.cloud.core)

    compileOnlyApi(libs.luckperms)
}

sourceSets.main {
    blossom.javaSources {
        property("name", rootProject.name)
        property("id", rootProject.ext.get("id")!!.toString())
        property("version", project.version.toString())
        property("description", project.description!!)
        property("author", rootProject.ext.get("author")!!.toString())
    }
}