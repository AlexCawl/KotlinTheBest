package org.alexcawl.space_gen_lib.system.threading

interface IHandler {
    fun handle(task: Runnable)

    fun post(task: Runnable)
}