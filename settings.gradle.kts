enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://repo.papermc.io/repository/maven-public/") // Paper
        maven("https://libraries.minecraft.net/")
        maven("https://mvn.lumine.io/repository/maven-public/") // Mythic
        maven("https://maven.enginehub.org/repo/")
        maven("https://repo.extendedclip.com/content/repositories/placeholderapi/") // PlaceholderAPI
        maven("https://jitpack.io")
        maven("https://repo.nexomc.com/releases")
        maven("https://maven.devs.beer/")
        maven("https://maven.enginehub.org/repo/") // WorldGuard
    }
}

pluginManagement {
    includeBuild("build-logic")
    repositories {
        maven("https://maven.architectury.dev/")
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "McPets"

include("api")
include("common")
include("bukkit")
include("velocity")

include("jar")