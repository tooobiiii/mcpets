package mcpets

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar.Companion.shadowJar
import org.gradle.internal.extensions.stdlib.toDefaultLowerCase

plugins {
    id("com.gradleup.shadow")
}

tasks {
    withType<Zip>().configureEach {
        isZip64 = true
    }

    // The shadow jar takes the empty classifier, so the thin jar has to move off that name —
    // otherwise both tasks declare the same output and whichever runs last overwrites the other.
    named<Jar>("jar") {
        archiveClassifier = "thin"
    }

    shadowJar {
        archiveClassifier = ""
        val libsPrefix = "${rootProject.group}.${rootProject.name.toDefaultLowerCase()}.common.libs"
        relocate("org.bstats", "${libsPrefix}.bstats")
        relocate("org.yaml.snakeyaml", "${libsPrefix}.org.yaml.snakeyaml")

        minimize {
            exclude(dependency("org.bstats:.*:.*"))
        }

        mergeServiceFiles()
    }
}