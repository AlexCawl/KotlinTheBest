package org.alexcawl.space_gen_lib.subject.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.space_gen_lib.subject.RotationOrientation

@Serializable
data class PlanetDTO(
    @SerialName("name") val name: String,
    @SerialName("weight") val weight: Double,
    @SerialName("radius") val radius: Double,
    @SerialName("distance_from_sun") val distanceFromSun: Double,
    @SerialName("rotation_time") val rotationTime: Long,
    @SerialName("rotation_type") val rotationType: RotationOrientation
)
