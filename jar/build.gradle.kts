import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar.Companion.shadowJar

plugins {
    id("mcpets.java.base")
    id("com.gradleup.shadow")
}

val platforms: List<Project> = rootProject.subprojects
    .filter { it != project }
    .map { evaluationDependsOn(it.path) }
    .filter { it.plugins.hasPlugin("mcpets.java.platform") }

tasks {
    jar {
        enabled = false
    }

    shadowJar {
        archiveFileName.set("${rootProject.name}-${project.version}.jar")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        fun registerPlatform(project: Project, jarTask: AbstractArchiveTask) {
            dependsOn(jarTask)
            dependsOn(project.tasks.withType<Jar>())
            from(zipTree(jarTask.archiveFile))
        }

        platforms.forEach { p ->
            val task = p.tasks.named<ShadowJar>("shadowJar").get()
            registerPlatform(p, task)
        }
    }
    build.get().dependsOn(shadowJar)
}