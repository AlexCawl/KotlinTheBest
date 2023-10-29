package org.alexcawl.space_gen_lib.subject.dto

import org.alexcawl.space_gen_lib.subject.Planet
import org.alexcawl.space_gen_lib.subject.Star

fun Planet.toDto(): PlanetDTO = PlanetDTO(
    this.name,
    this.weight,
    this.radius,
    this.distanceFromSun,
    this.rotationTime,
    this.rotationType
)

fun PlanetDTO.toModel(): Planet = Planet(
    this.weight,
    this.radius,
    this.rotationTime,
    this.rotationType,
    this.name,
    this.distanceFromSun
)

fun Star.toDto(): StarDTO = StarDTO(
    this.name,
    this.weight,
    this.radius
)

fun StarDTO.toModel(): Star = Star(
    this.weight,
    this.radius,
    this.name
)