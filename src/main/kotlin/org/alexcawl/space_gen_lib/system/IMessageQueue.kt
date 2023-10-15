package org.alexcawl.space_gen_lib.system

interface IMessageQueue {
    fun isEmpty(): Boolean

    fun add(message: Message)

    fun pop(): Message
}