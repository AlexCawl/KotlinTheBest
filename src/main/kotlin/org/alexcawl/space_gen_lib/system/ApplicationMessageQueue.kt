package org.alexcawl.space_gen_lib.system

import java.util.*

class ApplicationMessageQueue : MessageQueue {
    private val queue: MutableList<Pair<UUID, ApplicationMessage>> = mutableListOf()
    private val lock: Any = Any()
    override fun isEmpty(): Boolean = synchronized(lock) {
        queue.isEmpty()
    }

    override fun addMessage(message: ApplicationMessage): UUID {
        synchronized(lock) {
            val id: UUID = UUID.randomUUID()
            queue.add(Pair(id, message))
            return id
        }
    }

    override fun removeMessage(id: UUID): ApplicationMessage {
        synchronized(lock) {
            when (val message = queue.find { it.first == id }?.second) {
                null -> return ApplicationMessage.Empty
                else -> {
                    queue.removeIf { it.first == id }
                    return message
                }
            }
        }
    }

    override fun popMessage(): ApplicationMessage {
        synchronized(lock) {
            return when (val message = queue.removeFirstOrNull()?.second) {
                null -> ApplicationMessage.Empty
                else -> message
            }
        }
    }
}