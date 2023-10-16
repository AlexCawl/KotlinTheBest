package org.alexcawl.space_gen_lib.system.threading

import org.alexcawl.space_gen_lib.system.entity.Message
import java.util.LinkedList
import java.util.Queue

class MessageQueue : IMessageQueue {
    private val lock: Any = Any()
    private val queue: Queue<Message> = LinkedList()

    override fun isEmpty(): Boolean = synchronized(lock, queue::isEmpty)

    override fun add(message: Message): Unit = synchronized(lock) { queue.add(message) }

    override fun pop(): Message = synchronized(lock, queue::remove)
}