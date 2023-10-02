package org.alexcawl.space_gen_lib.system

import java.util.UUID

interface MessageQueue {
    fun isEmpty(): Boolean

    fun addMessage(message: ApplicationMessage): UUID

    fun removeMessage(id: UUID): ApplicationMessage

    fun popMessage(): ApplicationMessage
}