package org.alexcawl.space_gen_lib.system

interface ILooper {
    fun bindHandler(handler: IHandler)

    fun receive(message: Message)

    fun loop()
}