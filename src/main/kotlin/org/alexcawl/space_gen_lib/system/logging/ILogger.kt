package org.alexcawl.space_gen_lib.system.logging

import org.alexcawl.space_gen_lib.system.entity.Priority

interface ILogger {
    fun log(info: String)

    fun log(priority: Priority, info: String)
}