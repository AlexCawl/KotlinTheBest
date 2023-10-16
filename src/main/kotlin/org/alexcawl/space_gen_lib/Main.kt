package org.alexcawl.space_gen_lib

import org.alexcawl.space_gen_lib.system.util.setup
import org.alexcawl.space_gen_lib.system.logging.FileLogger
import org.alexcawl.space_gen_lib.system.threading.Looper

fun main() {
    val looper = Looper.prepare()
    val logger = FileLogger("log.txt")
    setup(looper, logger)
    looper.loop()
}

/*
ap Earth 1.0 2.0 1000.0 60
ap Mercury 1.0 1.0 500.0 60
as Sun 100.0 100.0
gd Earth Mercury 2
*/