package org.alexcawl.space_gen_lib.system.logging

import org.alexcawl.space_gen_lib.system.entity.Priority
import java.io.File
import java.time.LocalDateTime

class FileLogger(
    path: String
) : ILogger {
    private val file: File = File(path)

    override fun log(info: String) = file.appendText("${LocalDateTime.now()} | ${Priority.NORMAL} | $info\n")

    override fun log(priority: Priority, info: String) =
        file.appendText("${LocalDateTime.now()}  | $priority | $info\n")
}