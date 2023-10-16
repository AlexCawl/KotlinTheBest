package org.alexcawl.space_gen_lib.system.threading

import org.alexcawl.space_gen_lib.system.entity.Message

interface ILooper {
    fun bindHandler(handler: IHandler)

    fun receive(message: Message)

    fun loop()
}