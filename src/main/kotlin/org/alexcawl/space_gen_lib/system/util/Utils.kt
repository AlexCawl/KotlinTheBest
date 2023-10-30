package org.alexcawl.space_gen_lib.system.util

import org.alexcawl.space_gen_lib.subject.Planet
import org.alexcawl.space_gen_lib.subject.RotationOrientation
import org.alexcawl.space_gen_lib.subject.SpaceSystem
import org.alexcawl.space_gen_lib.subject.Star
import org.alexcawl.space_gen_lib.system.entity.ApplicationConfiguration
import org.alexcawl.space_gen_lib.system.entity.Command
import org.alexcawl.space_gen_lib.system.entity.Priority
import org.alexcawl.space_gen_lib.system.logging.ILogger
import org.alexcawl.space_gen_lib.system.marshalling.SpaceSystemDataSource
import org.alexcawl.space_gen_lib.system.threading.Handler
import org.alexcawl.space_gen_lib.system.threading.IHandler
import org.alexcawl.space_gen_lib.system.threading.ILooper
import java.io.FileInputStream
import java.util.*
import kotlin.concurrent.thread

const val ADD_PLANET: String = "ap"
const val ADD_STAR: String = "as"
const val GET_DISTANCE: String = "gd"
const val GET_SORTED: String = "gs"
const val SHOW_SYSTEM: String = "ss"
const val LOAD: String = "load"
const val SAVE: String = "save"

fun recognize(input: String): Command {
    val tokens: List<String> = input.trim().split(" ")
    val command: String = tokens[0]
    val args: List<String> = tokens.drop(1)
    return when (command) {
        ADD_PLANET -> Command.AddPlanetCommand(args)
        ADD_STAR -> Command.AddStarCommand(args)
        GET_DISTANCE -> Command.GetDistanceCommand(args)
        GET_SORTED -> Command.GetSortedCommand(args)
        SHOW_SYSTEM -> Command.ShowSystemCommand
        LOAD -> Command.Load(args[0])
        SAVE -> Command.Save(args[0])
        else -> Command.UndefinedCommand
    }
}

const val SORT_BY_RADIUS: String = "radius"
const val SORT_BY_WEIGHT: String = "weight"

fun setup(looper: ILooper, logger: ILogger?) {
    thread {
        val ioHandler: IHandler = Handler(looper)
        val builder: SpaceSystem.Builder = SpaceSystem.Builder()

        while (true) {
            val input = readln()
            try {
                val command = recognize(input)
                logger?.log(command.toString())
                when (command) {
                    is Command.AddPlanetCommand -> ioHandler.post {
                        val planet = Planet(
                            command.args[1].toDouble(),
                            command.args[2].toDouble(),
                            command.args[4].toLong(),
                            RotationOrientation.CLOCKWISE,
                            command.args[0],
                            command.args[3].toDouble()
                        )
                        builder.add(planet)
                    }

                    is Command.AddStarCommand -> ioHandler.post {
                        val star = Star(
                            command.args[1].toDouble(), command.args[2].toDouble(), command.args[0]
                        )
                        builder.add(star)
                    }

                    is Command.GetDistanceCommand -> {
                        val name1: String = command.args[0]
                        val name2: String = command.args[1]
                        val time: Long = command.args[2].toLong()
                        println(builder.build().getDistance(name1, name2, time))
                    }

                    is Command.ShowSystemCommand -> for (item in builder.build()) {
                        println(item)
                    }

                    is Command.GetSortedCommand -> when (val sortType = command.args[0]) {
                        SORT_BY_RADIUS -> builder.build().asSorted { o1, o2 -> o1.radius.compareTo(o2.radius) }
                        SORT_BY_WEIGHT -> builder.build().asSorted { o1, o2 -> o1.weight.compareTo(o2.weight) }
                        else -> throw UnsupportedOperationException("Sort type $sortType is not supported!")
                    }.forEach { item ->
                        println(item)
                    }

                    is Command.UndefinedCommand -> println("Command is undefined")

                    is Command.Load -> with(SpaceSystemDataSource) {
                        load(command.path).apply { builder.clear() }.forEach(builder::add)
                    }

                    is Command.Save -> SpaceSystemDataSource.save(builder.build(), command.path)
                }
            } catch (exception: RuntimeException) {
                val msg = exception.localizedMessage.toString()
                logger?.log(Priority.HIGH, msg)
                println(msg)
            }
        }
    }
}

fun readProperties(path: String): ApplicationConfiguration {
    val prop = Properties()
    FileInputStream(path).use { prop.load(it) }
    return ApplicationConfiguration(
        prop.getProperty("log.console").toBoolean(),
        prop.getProperty("log.file").toBoolean(),
        prop.getProperty("log.file.path")
    )
}