package org.alexcawl.space_gen_lib.system.entity

import org.alexcawl.space_gen_lib.system.threading.Handler

data class Message(
    val task: Runnable,
    val handler: Class<out Handler>
)
