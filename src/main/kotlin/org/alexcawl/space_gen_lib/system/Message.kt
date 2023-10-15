package org.alexcawl.space_gen_lib.system

data class Message(
    val task: Runnable,
    val handler: Class<out Handler>
)
