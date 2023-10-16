package org.alexcawl.space_gen_lib.system.threading

import org.alexcawl.space_gen_lib.system.entity.Message

interface IMessageQueue {
    fun isEmpty(): Boolean

    fun add(message: Message)

    fun pop(): Message
}