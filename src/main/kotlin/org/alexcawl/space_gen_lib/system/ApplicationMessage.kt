package org.alexcawl.space_gen_lib.system

sealed interface ApplicationMessage {
    object Empty : ApplicationMessage
    class AddPlanet(
        val name: String,
        val weight: Double,
        val radius: Double,
        val distance: Double,
        val yearInTime: Long
    ) : ApplicationMessage

    class AddStar(
        val name: String,
        val info: String,
        val weight: Double,
        val radius: Double
    ) : ApplicationMessage


    class GetDistance(
        val from: String,
        val to: String,
        val time: Long
    ) : ApplicationMessage
}