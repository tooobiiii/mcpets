plugins {
    id("base")
    `kotlin-dsl`
}

@SuppressWarnings("unresolved")
val versionCatalogAccessorsClasspath = files(libs.javaClass.superclass.protectionDomain.codeSource.location)

dependencies {
    implementation(libs.shadow.gradle.plugin)
    implementation(libs.lombok.gradle.plugin)
    implementation(libs.run.paper.gradle.plugin)
    implementation(libs.run.velocity.gradle.plugin)
    implementation(versionCatalogAccessorsClasspath)
}