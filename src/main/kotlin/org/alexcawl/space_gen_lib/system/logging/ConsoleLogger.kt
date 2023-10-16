package org.alexcawl.space_gen_lib.system.logging

import org.alexcawl.space_gen_lib.system.entity.Priority
import java.util.logging.Level
import java.util.logging.Logger

class ConsoleLogger : ILogger {
    private val logger by lazy { Logger.getLogger(this.javaClass.toString()) }
    override fun log(info: String) = logger.log(Level.INFO, info)

    override fun log(priority: Priority, info: String) = when (priority) {
        Priority.LOW -> logger.log(Level.ALL, info)
        Priority.NORMAL -> logger.log(Level.INFO, info)
        Priority.HIGH -> logger.log(Level.WARNING, info)
    }
}