package org.alexcawl.space_gen_lib.system

import org.alexcawl.space_gen_lib.entity.Planet
import org.alexcawl.space_gen_lib.entity.RotationOrientation
import org.alexcawl.space_gen_lib.entity.SpaceSystem
import org.alexcawl.space_gen_lib.entity.Star

class ApplicationHandler : Handler {
    private var builder = SpaceSystem.Companion.Builder()
    private val system: SpaceSystem
        get() = builder.build()

    override fun handle(message: ApplicationMessage) {
        when (message) {
            is ApplicationMessage.AddPlanet -> {
                builder = builder.addPlanet(
                    Planet(
                        message.weight,
                        message.radius,
                        message.yearInTime,
                        RotationOrientation.CLOCKWISE,
                        message.name,
                        message.distance
                    )
                )
            }

            is ApplicationMessage.AddStar -> {
                builder = builder.addStar(
                    Star(
                        message.weight,
                        message.radius,
                        message.name,
                        message.info
                    )
                )
            }

            is ApplicationMessage.Empty -> Unit

            is ApplicationMessage.GetDistance -> {
                try {
                    val distance: Double = system.getDistanceBetween(
                        message.from,
                        message.to,
                        message.time
                    )
                    println("Distance between ${message.from} -> ${message.to} = $distance")
                } catch (exception: IllegalStateException) {
                    println(exception.message)
                }
            }
        }
    }
}