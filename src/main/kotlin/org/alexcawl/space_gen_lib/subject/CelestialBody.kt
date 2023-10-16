package org.alexcawl.space_gen_lib.subject

import kotlin.math.pow

abstract class CelestialBody(
    val weight: Double,
    val radius: Double,
    private val rotationTime: Long,
    private val rotationType: RotationOrientation
) : Rotatable {
    override val angularVelocity: Double
        get() = 2 * Math.PI / rotationTime

    private fun getVolume(): Double = (4 / 3) * Math.PI * radius.pow(3)

    private fun getDensity(): Double = weight / getVolume()

    override fun getPlanetTrajectoryAngleWhen(time: Long): Double {
        val allTrajectoryRadians: Double = time * angularVelocity
        val cycles: Long = (allTrajectoryRadians / (Math.PI * 2)).toLong()
        return when (rotationType) {
            RotationOrientation.CLOCKWISE -> -1
            RotationOrientation.COUNTERCLOCKWISE -> 1
        } * (allTrajectoryRadians - cycles * Math.PI * 2)
    }

    open fun getStats(): String = StringBuilder()
        .append("Weight = $weight").append("\n")
        .append("Radius = $radius").append("\n")
        .append("Volume = ${getVolume()}").append("\n")
        .append("Density = ${getDensity()}").append("\n")
        .toString()
}