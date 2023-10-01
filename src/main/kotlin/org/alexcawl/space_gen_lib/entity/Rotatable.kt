package org.alexcawl.space_gen_lib.entity

interface Rotatable {
    val angularVelocity: Double

    fun getPlanetTrajectoryAngleWhen(time: Long): Double
}