plugins {
    id("net.fabricmc.fabric-loom-remap") version("1.17.+")
    id("ploceus") version("1.17.+")
}

group = "xyz.tryfle.freelookenabler"
version = "mod_version"()

ploceus {
    setIntermediaryGeneration(2)
}

dependencies {
    minecraft("com.mojang:minecraft:${"minecraft_version"()}")
    mappings(ploceus.layeredMappings {
        mappings("net.ornithemc:feather-gen2:${"minecraft_version"()}+build.${"feather_version"()}:v2") {
            containsUnpick()
        }
        mappings(rootProject.file("gradle/feather-overrides.tiny"))
    })

    modImplementation("net.fabricmc:fabric-loader:${"fabric_version"()}")
    ploceus.dependOsl("osl_version"())
    modCompileOnly(files("libs/freelook.jar")) // yes you need to put the jar in there
}

tasks.processResources {
    val v = project.version
    inputs.property("version", v)

    filesMatching("fabric.mod.json") {
        expand("version" to v)
    }
}

operator fun String.invoke() = rootProject.providers.gradleProperty(this).orNull
    ?: error("Property $this not found")
repositories {
    mavenCentral()
}