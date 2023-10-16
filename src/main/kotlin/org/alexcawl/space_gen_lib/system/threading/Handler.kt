package org.alexcawl.space_gen_lib.system.threading

import org.alexcawl.space_gen_lib.system.entity.Message

class Handler(
    private val looper: ILooper
): IHandler {
    init {
        looper.bindHandler(this)
    }

    override fun handle(task: Runnable): Unit = task.run()

    override fun post(task: Runnable): Unit = looper.receive(Message(task, this::class.java))
}