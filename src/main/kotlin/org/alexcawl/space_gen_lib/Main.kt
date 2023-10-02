package org.alexcawl.space_gen_lib

import org.alexcawl.space_gen_lib.system.ApplicationHandler
import org.alexcawl.space_gen_lib.system.ApplicationLooper

fun main(args: Array<String>) {
    ApplicationLooper(ApplicationHandler()).prepare()
}

/*
ap Earth 1.0 2.0 1000.0 60
ap Mercury 1.0 1.0 500.0 60
as Sun Our_sun 100.0 100.0
d Earth Mercury 2
*/