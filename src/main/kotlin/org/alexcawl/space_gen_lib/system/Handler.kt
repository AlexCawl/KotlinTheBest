package org.alexcawl.space_gen_lib.system

interface Handler {

    fun handle(message: ApplicationMessage)
}