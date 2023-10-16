package org.alexcawl.space_gen_lib.subject

import kotlin.math.*

class Planet(
    weight: Double,
    radius: Double,
    rotationTime: Long,
    rotationType: RotationOrientation,
    val name: String,
    val distanceFromSun: Double
) : CelestialBody(weight, radius, rotationTime, rotationType) {
    private fun getCoordinatesWhen(time: Long): Coordinates = getPlanetTrajectoryAngleWhen(time).run {
        return@run Coordinates(
            cos(this) * distanceFromSun,
            sin(this) * distanceFromSun
        )
    }

    fun getDistance(planet: Planet, time: Long): Double {
        val c1 = getCoordinatesWhen(time)
        val c2 = planet.getCoordinatesWhen(time)
        return sqrt((c1.x - c2.x).pow(2) + (c1.y - c2.y).pow(2))
    }

    override fun toString(): String = "Planet $name"

    override fun getStats(): String = StringBuilder()
        .append(this.toString()).append("\n")
        .append("Distance from sun = $distanceFromSun").append("\n")
        .append(super.getStats())
        .toString()
}