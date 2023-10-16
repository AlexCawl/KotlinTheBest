package org.alexcawl.space_gen_lib.subject

class Star(
    weight: Double,
    radius: Double,
    val name: String
) : CelestialBody(weight, radius, 0, RotationOrientation.CLOCKWISE) {
    override fun toString(): String = "Star $name"

    override fun getStats(): String = StringBuilder()
        .append(this.toString()).append("\n")
        .append(super.getStats())
        .toString()
}