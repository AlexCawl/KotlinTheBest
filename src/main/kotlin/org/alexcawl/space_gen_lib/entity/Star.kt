package org.alexcawl.space_gen_lib.entity

class Star(
    weight: Double,
    radius: Double,
    private val name: String,
    private val description: String
) : CelestialBody(weight, radius, 0, RotationOrientation.CLOCKWISE) {
    override fun toString(): String = "Star $name"

    override fun getStats(): String = StringBuilder()
        .append(this.toString()).append("\n")
        .append("Description = $description").append("\n")
        .append(super.getStats())
        .toString()
}