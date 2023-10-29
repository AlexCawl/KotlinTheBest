package org.alexcawl.space_gen_lib.system.marshalling

interface ILoader <T> {
    fun load(fromPath: String): T
}