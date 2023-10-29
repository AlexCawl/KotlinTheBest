package org.alexcawl.space_gen_lib.system.marshalling

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.alexcawl.space_gen_lib.subject.Planet
import org.alexcawl.space_gen_lib.subject.SpaceSystem
import org.alexcawl.space_gen_lib.subject.Star
import org.alexcawl.space_gen_lib.subject.dto.SpaceSystemDTO
import java.io.File

class SpaceSystemDataSource : IDataSource<SpaceSystem> {
    override fun load(fromPath: String): SpaceSystem {
        val file: File = File(fromPath)
        val system = Json.decodeFromString<SpaceSystemDTO>(
            file.inputStream().reader().readLines().joinToString("")
        )
        val builder = SpaceSystem.Companion.Builder()
        val star = Star(
            system.star.radius, system.star.weight, system.star.name
        )
        builder.add(star)
        system.planets.forEach {
            val planet = Planet(
                it.weight, it.radius, it.rotationTime, it.rotationType, it.name, it.distanceFromSun
            )
            builder.add(planet)
        }
        return builder.build()
    }

    override fun save(data: SpaceSystem, toPath: String) {
        val file = File(toPath)
        with(file) { writeText(Json.encodeToString(data)) }
    }
}