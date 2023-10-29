package org.alexcawl.space_gen_lib.system.entity

data class ApplicationConfiguration(
    val isConsoleLogging: Boolean,
    val isFileLogging: Boolean,
    val logPath: String? = null,
    val isLoadFromSource: Boolean = false,
    val sourcePath: String? = null
)
