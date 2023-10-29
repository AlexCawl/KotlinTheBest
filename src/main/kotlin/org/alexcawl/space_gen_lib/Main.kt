package org.alexcawl.space_gen_lib

import org.alexcawl.space_gen_lib.system.logging.ConsoleLogger
import org.alexcawl.space_gen_lib.system.util.setup
import org.alexcawl.space_gen_lib.system.logging.FileLogger
import org.alexcawl.space_gen_lib.system.logging.ILogger
import org.alexcawl.space_gen_lib.system.threading.Looper
import org.alexcawl.space_gen_lib.system.util.readProperties

const val CONFIG_PATH: String = "application.conf"

fun main() {
    val configuration = readProperties(CONFIG_PATH)
    val logger: ILogger? = when {
        configuration.isConsoleLogging -> ConsoleLogger()
        configuration.isFileLogging && configuration.logPath != null -> FileLogger(configuration.logPath)
        else -> null
    }
    val looper = Looper.prepare()
    setup(looper, logger)
    looper.loop()
}

/*
ap Earth 1.0 2.0 1000.0 60
ap Mercury 1.0 1.0 500.0 60
as Sun 100.0 100.0
gd Earth Mercury 2
*/