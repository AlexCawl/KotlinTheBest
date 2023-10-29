package org.alexcawl.space_gen_lib.subject.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpaceSystemDTO(
    @SerialName("star") val star: StarDTO,
    @SerialName("planets") val planets: List<PlanetDTO>
)