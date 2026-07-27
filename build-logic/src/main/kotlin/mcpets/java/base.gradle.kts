package mcpets.java

import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    `java-library`
    id("io.freefair.lombok")
}

base.archivesName.set("${rootProject.name}-${project.name}")

tasks {
    processResources {
        filter<ReplaceTokens>(
            "tokens" to mapOf(
                "id" to rootProject.ext.get("id")!!.toString(),
                "name" to rootProject.name,
                "version" to project.version,
                "description" to project.description,
                "author" to rootProject.ext.get("author")!!.toString()
            )
        )
    }

    javadoc {
        enabled = false
        options.encoding = Charsets.UTF_8.name()
        (options as StandardJavadocDocletOptions).addStringOption("Xdoclint:none", "-quiet")
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(25)
        options.compilerArgs.addAll(listOf("-nowarn", "-Xlint:-unchecked", "-Xlint:-deprecation"))
    }
}

java.toolchain {
    languageVersion.set(JavaLanguageVersion.of(25))
}
