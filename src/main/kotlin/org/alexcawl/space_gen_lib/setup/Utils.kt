package org.alexcawl.space_gen_lib.setup

import org.alexcawl.space_gen_lib.entity.Planet
import org.alexcawl.space_gen_lib.entity.RotationOrientation
import org.alexcawl.space_gen_lib.entity.SpaceSystem
import org.alexcawl.space_gen_lib.entity.Star
import org.alexcawl.space_gen_lib.system.Handler
import org.alexcawl.space_gen_lib.system.IHandler
import org.alexcawl.space_gen_lib.system.ILooper
import kotlin.concurrent.thread

const val ADD_PLANET: String = "ap"
const val ADD_STAR: String = "as"
const val GET_DISTANCE: String = "gd"
const val GET_SORTED: String = "gs"

@Suppress("DEPRECATION")
fun recognize(input: String): Command {
    val tokens: List<String> = input.strip().split(" ")
    // validation
    val command: String = tokens[0]
    val args: List<String> = tokens.drop(1)
    return when (command) {
        ADD_PLANET -> Command.AddPlanetCommand(args)
        ADD_STAR -> Command.AddStarCommand(args)
        GET_DISTANCE -> Command.GetDistanceCommand(args)
        GET_SORTED -> Command.GetSortedCommand(args)
        else -> Command.UndefinedCommand
    }
}

fun setup(looper: ILooper) {
    thread {
        val ioHandler: IHandler = Handler(looper)
        val builder: SpaceSystem.Companion.Builder = SpaceSystem.Companion.Builder()

        while (true) {
            val input = readln()
            when (val command = recognize(input)) {
                is Command.AddPlanetCommand -> ioHandler.post {
                    val planet = Planet(
                        command.args[1].toDouble(),
                        command.args[2].toDouble(),
                        command.args[4].toLong(),
                        RotationOrientation.CLOCKWISE,
                        command.args[0],
                        command.args[3].toDouble()
                    )
                    builder.addPlanet(planet)
                }

                is Command.AddStarCommand -> ioHandler.post {
                    val star = Star(
                        command.args[1].toDouble(),
                        command.args[2].toDouble(),
                        command.args[0]
                    )
                    builder.addStar(star)
                }

                is Command.GetDistanceCommand -> {
                    val name1: String = command.args[0]
                    val name2: String = command.args[1]
                    val time: Long = command.args[2].toLong()
                    println(builder.build().getDistanceBetween(name1, name2, time))
                }

                is Command.GetSortedCommand -> TODO()
                is Command.UndefinedCommand -> TODO()
                is Command.InvalidArgs -> TODO()
            }
        }
    }
}