package org.alexcawl.space_gen_lib.subject

interface Rotatable {
    val angularVelocity: Double

    fun getPlanetTrajectoryAngleWhen(time: Long): Double
}