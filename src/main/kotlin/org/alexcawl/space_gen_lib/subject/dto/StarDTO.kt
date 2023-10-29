package org.alexcawl.space_gen_lib.subject.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StarDTO(
    @SerialName("name") val name: String,
    @SerialName("weight") val weight: Double,
    @SerialName("radius") val radius: Double
)