package org.alexcawl.space_gen_lib.system.marshalling

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.alexcawl.space_gen_lib.subject.Planet
import org.alexcawl.space_gen_lib.subject.SpaceSystem
import org.alexcawl.space_gen_lib.subject.dto.PlanetDTO
import org.alexcawl.space_gen_lib.subject.dto.SpaceSystemDTO
import org.alexcawl.space_gen_lib.subject.dto.toDto
import org.alexcawl.space_gen_lib.subject.dto.toModel
import java.io.File

object SpaceSystemDataSource : IDataSource<SpaceSystem> {
    override fun load(fromPath: String): SpaceSystem {
        val file = File(fromPath)
        val system = Json.decodeFromString<SpaceSystemDTO>(
            file.inputStream().reader().readLines().joinToString("")
        )
        val builder = SpaceSystem.Builder()
        builder.add(system.star.toModel())
        system.planets.map(PlanetDTO::toModel).forEach { planet: Planet -> builder.add(planet) }
        return builder.build()
    }

    override fun save(data: SpaceSystem, toPath: String) {
        val dto = SpaceSystemDTO(
            data.star.toDto(),
            data.planets.map(Planet::toDto)
        )
        val file = File(toPath)
        with(file) { writeText(Json.encodeToString(dto)) }
    }
}