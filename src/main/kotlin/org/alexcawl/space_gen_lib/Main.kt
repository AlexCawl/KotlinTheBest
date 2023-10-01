package org.alexcawl.space_gen_lib

import org.alexcawl.space_gen_lib.entity.Planet
import org.alexcawl.space_gen_lib.entity.RotationOrientation
import org.alexcawl.space_gen_lib.entity.SpaceSystem
import org.alexcawl.space_gen_lib.entity.Star

fun main(args: Array<String>) {
    val system = SpaceSystem.Companion.Builder().addStar(
        Star(100.0, 100.0, "Sun", "Our sun!")
    ).addPlanet(
        Planet(1.0, 1.0, 60, RotationOrientation.COUNTERCLOCKWISE, "Earth", 1000.0)
    ).addPlanet(
        Planet(1.0, 1.0, 60, RotationOrientation.COUNTERCLOCKWISE, "Mercury", 500.0)
    ).build()

    print(system.getDistanceBetween("Earth", "Mercury", 3))
}