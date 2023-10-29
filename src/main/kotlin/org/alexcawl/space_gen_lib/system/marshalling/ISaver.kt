package org.alexcawl.space_gen_lib.system.marshalling

interface ISaver <T> {
    fun save(data: T, toPath: String)
}