@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    `version-catalog`
    `maven-publish`
}

group = "o.sur.examples"
version = "1.0.0-SNAPSHOT"

catalog {
    versionCatalog {
        from(files("libs.versions.toml"))
    }
}

publishing {
    repositories {
        maven {
            url = uri("https://o.surovtsev.net/repository/maven-releases/")
            credentials {
                val nexusUsername: String? by project
                val nexusPassword: String? by project
                username = nexusUsername
                password = nexusPassword
            }
        }
    }
    publications {
        create<MavenPublication>("version-catalog") {
            groupId = "$group"
            artifactId = project.name
            version = version
            from(components["versionCatalog"])
        }
    }
}

tasks.register("clean") {
    doLast {
        delete("${rootDir.path}/build")
        println("Default Cleaning!")
    }
}
